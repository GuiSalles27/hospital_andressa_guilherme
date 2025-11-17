/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hospital.controlador;

import com.mycompany.hospital.dao.UsuarioDAO;
import com.mycompany.hospital.dao.entidade.Usuario;
import com.mycompany.hospital.servico.WebConstante;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author tulio
 */
@WebServlet(WebConstante.BASE_PATH + "/LoginControlador")

public class LoginControlador extends HttpServlet {

    private UsuarioDAO usuarioDAO;
    String opcao = "";
    String username = "";
    String password = "";
    String email = "";
    Usuario usuario;

    @Override
    public void init() throws ServletException {
        usuarioDAO = new UsuarioDAO();

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
             opcao = request.getParameter("opcao");
             username = request.getParameter("username");
             password = request.getParameter("password");
             email = request.getParameter("email");

           
            usuario = usuarioDAO.buscarUsuarioPorNomeUsuario(username);

            switch (opcao) {
                case "cadastrar":
                    cadastrar(request, response);
                    break;
                case "login":
                    login(request, response);
                    break;
                    case "logout":
                    logout(request, response);
                    break;
                default:
                    throw new IllegalArgumentException("Opção inválida" + opcao);
            }
        } catch (NumberFormatException e) {
            response.getWriter().println("Erro: um ou mais parâmetros não são numeros válidos");
        } catch (IllegalArgumentException e) {
            response.getWriter().println("Erro: " + e.getMessage());
        }

    }

    protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (usuario != null && BCrypt.checkpw(password, usuario.getSenhaHash())) {
            request.getSession().setAttribute("user", username);
            response.sendRedirect("/homepage.jsp");
        } else {
            request.setAttribute("mensagem", "Usuário ou senha inválidos");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }

    }
    
     private void cadastrar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        usuarioDAO.registrarUsuario(username, password, email);
        request.setAttribute("mensagem", "Usuário cadastrado com sucesso!");
        RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
        dispatcher.forward(request, response);

    }
      @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    logout(request, response);
    
    }
       protected void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
          HttpSession session = request.getSession(false); // Obter a sessão, não criar uma nova
        if (session != null) {
            session.invalidate(); // Encerra a sessão
             request.setAttribute("mensagem", "Sessão encerrada");
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
           dispatcher.forward(request, response);
        //response.sendRedirect("/estudoCasoSI/login.jsp");
       
       }
}


/*
    Criptografia de Senhas: Utilize o bcrypt para criptografar as senhas antes de armazená-las no banco de dados.

Banco de Dados: Certifique-se de que sua tabela de usuários contém colunas para username e password_hash.

Dependência do Bcrypt: Adicione a biblioteca Bcrypt ao seu projeto para utilizar a criptografia de senhas.
    
     <dependency>
            <groupId>org.mindrot</groupId>
            <artifactId>jbcrypt</artifactId>
            <version>0.4</version>
        </dependency>
    
    // Criar um hash de senha
String password = "minhaSenhaSegura";
String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());

// Verificar uma senha
boolean passwordMatch = BCrypt.checkpw(password, hashedPassword);
    
         */
