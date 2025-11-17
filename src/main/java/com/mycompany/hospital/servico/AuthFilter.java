package com.mycompany.hospital.servico;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Filtro de autenticação global.
 * Intercepta todas as requisições e verifica se o usuário está logado.
 * Páginas públicas (como login e homepage) não exigem login.
 */
@WebFilter(urlPatterns = {"/*"})
public class AuthFilter implements Filter {

    public AuthFilter() {
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Inicialização do filtro, se necessário
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        // Obtém o caminho relativo à aplicação
        String path = httpRequest.getRequestURI().substring(httpRequest.getContextPath().length());

        // ----------------------------
        // 1. Lista de URLs públicas
        // ----------------------------
        // Essas páginas podem ser acessadas sem login
        boolean isPublicPath = path.startsWith("/public/") ||
                path.equalsIgnoreCase("/login.jsp") ||          // Página de login
                path.equalsIgnoreCase("/homepage.jsp") ||       // NOVA ALTERAÇÃO: homepage é pública
                path.equalsIgnoreCase("/esqueci-senha.jsp") ||  // Recuperação de senha
                path.equalsIgnoreCase("/reset-senha.jsp") ||
                path.equalsIgnoreCase("/CadastroUsuario.jsp");  // Cadastro de usuário
                

        // ----------------------------
        // 2. Permite acesso ao LoginControlador sem login
        // ----------------------------
        // NOVA ALTERAÇÃO: evita bloqueio das requisições de login
        isPublicPath = isPublicPath || path.equalsIgnoreCase("/LoginControlador");

        // ----------------------------
        // 3. Permite acesso a arquivos estáticos (CSS, JS, imagens)
        // ----------------------------
        // NOVA ALTERAÇÃO: evita redirecionamento do filtro para arquivos de estilo
        isPublicPath = isPublicPath || path.contains("/estilo/") || path.contains("/img/");

        // ----------------------------
        // 4. Decisão do filtro
        // ----------------------------
        if (isPublicPath) {
            // Página pública, continua normalmente
            chain.doFilter(request, response);
        } else {
            // Página protegida, verifica se usuário está logado
            if (httpRequest.getSession().getAttribute("user") == null) {
                // NOVA ALTERAÇÃO: redireciona dinamicamente usando getContextPath()
                httpResponse.sendRedirect(httpRequest.getContextPath() + "/login.jsp");
            } else {
                // Usuário logado, continua a requisição
                chain.doFilter(request, response);
            }
        }
    }

    @Override
    public void destroy() {
        // Limpeza de recursos, se necessário
    }
}
