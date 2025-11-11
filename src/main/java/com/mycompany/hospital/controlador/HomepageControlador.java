package com.mycompany.hospital.controlador;

import com.mycompany.hospital.servico.WebConstante;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Controlador para a Homepage.
 * Apenas encaminha para a página inicial.
 * @author Andressa & Guilherme
 */
@WebServlet(name = "HomepageControlador", urlPatterns = {"/homepage"})
public class HomepageControlador extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Define a URL base para os links no JSP, garantindo que o menu funcione
        request.setAttribute("URL_BASE", WebConstante.BASE_PATH);

        // Encaminha para a página JSP da homepage
        RequestDispatcher dispatcher = request.getRequestDispatcher("/homepage.jsp");
        dispatcher.forward(request, response);
    }
}
