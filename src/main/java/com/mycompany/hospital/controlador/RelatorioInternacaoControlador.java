package com.mycompany.hospital.controlador;

import com.mycompany.hospital.dao.InternacaoDAO;
import com.mycompany.hospital.dao.entidade.Internacao;
import com.mycompany.hospital.relatorios.RelatorioInternacaoPacienteExcel;
import com.mycompany.hospital.servico.WebConstante;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(WebConstante.BASE_PATH + "/RelatorioInternacaoControlador")
public class RelatorioInternacaoControlador extends HttpServlet {

    private InternacaoDAO internacaoDAO = new InternacaoDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idPaciente = Integer.parseInt(request.getParameter("codPaciente"));

        List<Internacao> internacoes = internacaoDAO.buscarPorPaciente(idPaciente);

        RelatorioInternacaoPacienteExcel relatorio = new RelatorioInternacaoPacienteExcel(internacoes);
        relatorio.gerarRelatorio((jakarta.servlet.http.HttpServletResponse) response);
    }
}
