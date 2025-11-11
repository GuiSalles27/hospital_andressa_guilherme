package com.mycompany.hospital.controlador;

import com.mycompany.hospital.dao.FuncionarioDAO;
import com.mycompany.hospital.dao.entidade.Funcionario;
import com.mycompany.hospital.relatorios.RelatorioFuncionarioExcel;
import com.mycompany.hospital.servico.WebConstante;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(WebConstante.BASE_PATH + "/RelatorioFuncionarioControlador")
public class RelatorioFuncionarioControlador extends HttpServlet {

    private FuncionarioDAO dao = new FuncionarioDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        List<Funcionario> lista = dao.buscarTodosComCargoHospital();
        RelatorioFuncionarioExcel relatorio = new RelatorioFuncionarioExcel(lista);
        relatorio.gerarRelatorio((jakarta.servlet.http.HttpServletResponse) resp);
    }
}
