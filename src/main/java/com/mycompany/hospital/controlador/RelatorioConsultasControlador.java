package com.mycompany.hospital.controlador;

import com.mycompany.hospital.dao.ConsultaDAO;
import com.mycompany.hospital.dao.entidade.Consulta;
import com.mycompany.hospital.relatorios.RelatorioConsultasExcel;
import com.mycompany.hospital.servico.WebConstante;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(WebConstante.BASE_PATH + "/RelatorioConsultasControlador")
public class RelatorioConsultasControlador extends HttpServlet {

    private final ConsultaDAO consultaDAO = new ConsultaDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Consulta> consultas = consultaDAO.buscarTodasConsultas();

        RelatorioConsultasExcel relatorio = new RelatorioConsultasExcel(consultas);
        relatorio.gerarRelatorio((jakarta.servlet.http.HttpServletResponse) response);
    }
}
