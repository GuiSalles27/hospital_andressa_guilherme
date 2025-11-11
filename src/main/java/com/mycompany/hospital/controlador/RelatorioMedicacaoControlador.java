package com.mycompany.hospital.controlador;

import com.mycompany.hospital.dao.MedicacaoDAO;
import com.mycompany.hospital.dao.entidade.Medicacao;
import com.mycompany.hospital.relatorios.RelatorioMedicacaoExcel;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/RelatorioMedicacaoControlador")
public class RelatorioMedicacaoControlador extends HttpServlet {

    private MedicacaoDAO medicacaoDao = new MedicacaoDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String tipo = request.getParameter("tipo"); // parâmetro vindo da tela
        List<Medicacao> lista = medicacaoDao.buscarPorTipo(tipo); // método que filtra no DAO

        RelatorioMedicacaoExcel relatorio = new RelatorioMedicacaoExcel(lista);
        relatorio.gerarRelatorio((jakarta.servlet.http.HttpServletResponse) response);
    }
}
