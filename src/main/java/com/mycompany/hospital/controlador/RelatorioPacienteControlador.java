package com.mycompany.hospital.controlador;

import com.mycompany.hospital.dao.PacienteDAO;
import com.mycompany.hospital.dao.entidade.Paciente;
import com.mycompany.hospital.relatorios.RelatorioPacienteExcel;
import com.mycompany.hospital.servico.WebConstante;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(WebConstante.BASE_PATH + "/RelatorioPacienteControlador")
public class RelatorioPacienteControlador extends HttpServlet {

    private PacienteDAO dao = new PacienteDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        
        List<Paciente> lista = dao.buscarTodosPacientePlano();

        RelatorioPacienteExcel relatorio = new RelatorioPacienteExcel(lista);
        relatorio.gerarRelatorio((jakarta.servlet.http.HttpServletResponse) resp);
    }
}
