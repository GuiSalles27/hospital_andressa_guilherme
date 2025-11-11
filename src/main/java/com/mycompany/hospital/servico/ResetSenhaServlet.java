package com.mycompany.hospital.servico;

import com.mycompany.hospital.dao.PasswordResetTokenDAO;
import com.mycompany.hospital.dao.UsuarioDAO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.mindrot.jbcrypt.BCrypt;

@WebServlet("/reset-senha")
public class ResetSenhaServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String token = request.getParameter("token");
        if (token == null || token.trim().isEmpty()) {
            request.setAttribute("mensagem", "Token ausente.");
            request.getRequestDispatcher("reset-senha.jsp").forward(request, response);
            return;
        }

        try {
            String tokenHash = PasswordResetTokenDAO.sha256Hex(token);
            Integer userId = new PasswordResetTokenDAO().validateToken(tokenHash);
            if (userId == null) {
                request.setAttribute("mensagem", "Token inválido ou expirado.");
                request.getRequestDispatcher("reset-senha.jsp").forward(request, response);
                return;
            }
            // token válido -> encaminha JSP com token (usado no campo hidden)
            request.setAttribute("token", token);
            request.getRequestDispatcher("reset-senha.jsp").forward(request, response);
        } catch (Exception ex) {
            throw new ServletException(ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String token = request.getParameter("token");
        String senha = request.getParameter("senha");
        String confirma = request.getParameter("confirma");

        if (token == null || token.trim().isEmpty()) {
            request.setAttribute("mensagem", "Token inválido.");
            request.getRequestDispatcher("reset-senha.jsp").forward(request, response);
            return;
        }

        if (senha == null || confirma == null || !senha.equals(confirma)) {
            request.setAttribute("mensagem", "Senhas não conferem.");
            request.setAttribute("token", token);
            request.getRequestDispatcher("reset-senha.jsp").forward(request, response);
            return;
        }

        if (senha.length() < 8) {
            request.setAttribute("mensagem", "A senha precisa ter pelo menos 8 caracteres.");
            request.setAttribute("token", token);
            request.getRequestDispatcher("reset-senha.jsp").forward(request, response);
            return;
        }

        try {
            String tokenHash = PasswordResetTokenDAO.sha256Hex(token);
            Integer userId = new PasswordResetTokenDAO().validateToken(tokenHash);
            if (userId == null) {
                request.setAttribute("mensagem", "Token inválido ou expirado.");
                request.getRequestDispatcher("reset-senha.jsp").forward(request, response);
                return;
            }

            // hash bcrypt e atualiza a senha do usuário
            String bcrypt = BCrypt.hashpw(senha, BCrypt.gensalt(10));
            new UsuarioDAO().atualizarSenha(userId, bcrypt);

            // marca token como usado
            new PasswordResetTokenDAO().markTokenUsed(tokenHash);

            // sucesso -> enviar para login
            request.setAttribute("sucesso", "Senha atualizada com sucesso. Faça login com a nova senha.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        } catch (Exception ex) {
            throw new ServletException(ex);
        }
    }
}
