package com.mycompany.hospital.servico;

import com.mycompany.hospital.dao.ConnectionFactory;
import com.mycompany.hospital.dao.PasswordResetTokenDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@WebServlet("/esqueci-senha")
public class EsqueciSenhaServlet extends HttpServlet {

    // true = apenas imprime link no console
    // false = envia email de verdade via SMTP (pegando configs do banco)
    private static final boolean EMAIL_DEBUG = false;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        if (email == null || email.trim().isEmpty()) {
            request.setAttribute("mensagem", "Por favor, informe seu e-mail.");
            request.getRequestDispatcher("esqueci-senha.jsp").forward(request, response);
            return;
        }

        try (Connection con = ConnectionFactory.getInstance().getConnection()) {
            // Busca usuário pelo e-mail
            String sql = "SELECT id FROM usuario WHERE email = ?";
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setString(1, email);
                ResultSet rs = ps.executeQuery();
                if (!rs.next()) {
                    // Resposta genérica para não revelar se o e-mail existe
                    request.setAttribute("mensagem", "Se o e-mail existir, você receberá um link para redefinir a senha.");
                    request.getRequestDispatcher("esqueci-senha.jsp").forward(request, response);
                    return;
                }

                int userId = rs.getInt("id");

                // Gera token e salva no BD
                String token = PasswordResetTokenDAO.generateToken();
                String tokenHash = PasswordResetTokenDAO.sha256Hex(token);
                new PasswordResetTokenDAO().saveToken(userId, tokenHash, 60); // expira em 60 min

                // Base URL robusta (config -> proxy -> request -> env)
                String baseUrl = resolveBaseUrl(request);

                // Constrói link de reset
                String resetUrl = baseUrl + "/reset-senha?token=" + token;

                // Envia email ou imprime no console
                if (EMAIL_DEBUG) {
                    System.out.println("=== Link de redefinição de senha ===");
                    System.out.println(resetUrl);
                } else {
                    try {
                        EmailService.enviarRecuperacaoSenha(email, resetUrl);
                    } catch (Exception ex) {
                        // fallback: loga o link no console
                        System.out.println("Falha ao enviar e-mail, imprimindo link:");
                        System.out.println(resetUrl);
                        ex.printStackTrace();
                    }
                }

                request.setAttribute("mensagem", "Se o e-mail existir, você receberá um link para redefinir a senha.");
                request.getRequestDispatcher("esqueci-senha.jsp").forward(request, response);
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    private String resolveBaseUrl(HttpServletRequest request) {
        // 1) Override via web.xml (recomendado)
        String override = getServletContext().getInitParameter("app.baseUrl");
        if (override != null && !override.isBlank()) {
            return stripTrailingSlash(override);
        }

        // 2) Cabeçalhos de proxy (se estiver atrás de proxy / túnel)
        String fProto = header(request, "X-Forwarded-Proto");
        String fHost  = header(request, "X-Forwarded-Host");
        String fPort  = header(request, "X-Forwarded-Port");

        String scheme = (fProto != null && !fProto.isBlank()) ? fProto : request.getScheme();

        String hostPort;
        if (fHost != null && !fHost.isBlank()) {
            hostPort = fHost; // pode já incluir porta
            if (fPort != null && !fPort.isBlank() && !fHost.contains(":")
                    && !("http".equalsIgnoreCase(scheme) && "80".equals(fPort))
                    && !("https".equalsIgnoreCase(scheme) && "443".equals(fPort))) {
                hostPort = hostPort + ":" + fPort;
            }
        } else {
            String host = request.getServerName();
            int port = request.getServerPort();
            String portPart = (!("http".equalsIgnoreCase(scheme) && port == 80)
                    && !("https".equalsIgnoreCase(scheme) && port == 443)) ? (":" + port) : "";
            hostPort = host + portPart;
        }

        String candidate = scheme + "://" + hostPort + request.getContextPath();

        // 3) Se ainda for localhost, tenta variável de ambiente APP_BASE_URL
        if (hostPort.startsWith("localhost") || hostPort.startsWith("127.0.0.1")
                || hostPort.startsWith("::1") || hostPort.startsWith("[::1]")) {
            String env = System.getenv("APP_BASE_URL");
            if (env != null && !env.isBlank()) {
                return stripTrailingSlash(env);
            }
        }

        return stripTrailingSlash(candidate);
    }

    private String header(HttpServletRequest req, String name) {
        String v = req.getHeader(name);
        return v != null ? v.trim() : null;
    }

    private String stripTrailingSlash(String s) {
        return (s != null && s.endsWith("/")) ? s.substring(0, s.length() - 1) : s;
    }
}
