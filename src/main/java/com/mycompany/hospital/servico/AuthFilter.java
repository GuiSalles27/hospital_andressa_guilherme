/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hospital.servico;


import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 * @author tulio
 */

@WebFilter(urlPatterns = {"/*"})
/*Um filtro em Java EE é uma classe Java que pode ser usada para interceptar solicitações a um aplicativo antes que elas cheguem a 
um servlet ou JSP. Isso é útil para verificar se um usuário está logado antes de permitir acesso a páginas restritas.*/
public class AuthFilter implements Filter {

    public AuthFilter() {
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Configuração de inicialização do filtro, se necessário.
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        // Obter o caminho da requisição
        String path = httpRequest.getRequestURI().substring(httpRequest.getContextPath().length());

        // String path = httpRequest.getServletPath();
        //System.out.println("PATH:" + path);
        // Verifica se "user" está na sessão
        // System.out.println("USER: "+httpRequest.getSession().getAttribute("user"));
        if (path.startsWith("/public/") || path.equalsIgnoreCase("/login.jsp")
                || path.equalsIgnoreCase("/com/mycompany/hospital_andressa_guilherme/controlador/LoginControlador")
           
                || path.equalsIgnoreCase("/index.jsp") || path.equalsIgnoreCase("/menu.jsp")|| path.equalsIgnoreCase("/CadastroUsuario.jsp")
                || path.contains("/estilo/") || path.equalsIgnoreCase("/CadastroAmbulatorio/") || path.equalsIgnoreCase("/CadastroCargo/") || path.equalsIgnoreCase("/CadastroConsulta/") || path.equalsIgnoreCase("/CadastroExame/") || path.equalsIgnoreCase("/CadastroFuncionario/") || path.equalsIgnoreCase("/CadastroHospital/") || path.equalsIgnoreCase("/CadastroInternacao/") || path.equalsIgnoreCase("/CadastroMedicacao/") || path.equalsIgnoreCase("/CadastroMedicacaoInternacao/") || path.equalsIgnoreCase("/CadastroPaciente/") || path.equalsIgnoreCase("/CadastroPlano/") || path.equalsIgnoreCase("/CadastroHospital/") || path.equalsIgnoreCase("/esqueci-senha") || path.equalsIgnoreCase("/esqueci-senha.jsp") || path.equalsIgnoreCase("/reset-senha") || path.equalsIgnoreCase("/reset-senha.jsp")) {
            chain.doFilter(request, response); // Continuar sem bloquear essas requisições
        } else {
            if (httpRequest.getSession().getAttribute("user") == null) {
                httpResponse.sendRedirect("/hospital_andressa_guilherme/login.jsp"); // Redirecionar para a página de login

            } else {
                chain.doFilter(request, response); // Usuário está logado, continue com a solicitação.
            }
        }
    }

    @Override
    public void destroy() {
        // Recursos de limpeza do filtro, se necessário.
    }
}
