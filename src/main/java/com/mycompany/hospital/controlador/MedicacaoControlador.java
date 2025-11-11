/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hospital.controlador;

import com.mycompany.hospital.dao.MedicacaoDAO;
import com.mycompany.hospital.dao.entidade.Medicacao;
import com.mycompany.hospital.servico.WebConstante;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(WebConstante.BASE_PATH+"/MedicacaoControlador")
public class MedicacaoControlador extends HttpServlet {
    private MedicacaoDAO objMedicacaoDao;
    String codRemedio, tipoRemedio, valorRemedio, nomeRemedio, fabricacao, desconto;

    @Override
    public void init() throws ServletException {
        objMedicacaoDao = new MedicacaoDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        try {
            String opcao = request.getParameter("opcao");
            if(opcao==null||opcao.isEmpty()){
                opcao="listar";
            }
            codRemedio = request.getParameter("codRemedio");
            tipoRemedio = request.getParameter("tipoRemedio");
            valorRemedio = request.getParameter("valorRemedio");
            nomeRemedio = request.getParameter("nomeRemedio");
            fabricacao = request.getParameter("fabricacao");
            desconto = request.getParameter("desconto");

            switch (opcao) {
                case "listar": encaminharParaPagina(request, response); break;
                case "cadastrar": cadastrar(request,response); break;
                case "editar": editar(request, response); break;
                case "confirmarEditar": confirmarEditar(request, response); break;
                case "excluir": excluir(request, response); break;
                case "confirmarExcluir": confirmarExcluir(request, response); break;
                case "cancelar": cancelar(request, response); break;
                default: throw new IllegalArgumentException("Opção inválida "+opcao);
            }
        } catch(NumberFormatException e) {
            response.getWriter().println("Erro: um ou mais parâmetros não são números válidos. Detalhes: "+e.getMessage());
        } catch(IllegalArgumentException e) {
            response.getWriter().println("Erro: Parâmetros ausentes "+e.getMessage());
        }
    }

    protected void cadastrar(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException {

    // Lê parâmetros diretamente do request (mais seguro)
    String tipoRemedio = request.getParameter("tipoRemedio");
    String valorRemedio = request.getParameter("valorRemedio");
    String nomeRemedio = request.getParameter("nomeRemedio");
    String fabricacao = request.getParameter("fabricacao");
    String desconto = request.getParameter("desconto");

    // Validação de campos obrigatórios
    if (tipoRemedio == null || tipoRemedio.trim().isEmpty() ||
        valorRemedio == null || valorRemedio.trim().isEmpty() ||
        nomeRemedio == null || nomeRemedio.trim().isEmpty() ||
        fabricacao == null || fabricacao.trim().isEmpty() ||
        desconto == null || desconto.trim().isEmpty()) {

        request.setAttribute("mensagem", "Erro: preencha todos os campos obrigatórios.");
        request.setAttribute("codRemedio", request.getParameter("codRemedio"));
        request.setAttribute("tipoRemedio", tipoRemedio);
        request.setAttribute("valorRemedio", valorRemedio);
        request.setAttribute("nomeRemedio", nomeRemedio);
        request.setAttribute("fabricacao", fabricacao);
        request.setAttribute("desconto", desconto);
        request.setAttribute("opcao", "cadastrar");
        encaminharParaPagina(request, response);
        return;
    }

    try {
        Medicacao nova = new Medicacao();
        nova.setTipoRemedio(tipoRemedio);
        nova.setValorRemedio(Double.parseDouble(valorRemedio));
        nova.setNomeRemedio(nomeRemedio);
        nova.setFabricacao(fabricacao);
        nova.setDesconto(Double.parseDouble(desconto));

        objMedicacaoDao.salvar(nova);
        encaminharParaPagina(request, response);

    } catch (NumberFormatException e) {
        request.setAttribute("mensagem", "Erro: preencha corretamente os campos numéricos (valor e desconto).");
        request.setAttribute("codRemedio", request.getParameter("codRemedio"));
        request.setAttribute("tipoRemedio", tipoRemedio);
        request.setAttribute("valorRemedio", valorRemedio);
        request.setAttribute("nomeRemedio", nomeRemedio);
        request.setAttribute("fabricacao", fabricacao);
        request.setAttribute("desconto", desconto);
        request.setAttribute("opcao", "cadastrar");
        encaminharParaPagina(request, response);
    }
}


    private void encaminharParaPagina(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        List<Medicacao> listaMedicacao = objMedicacaoDao.buscarTodasMedicacoes();
        request.setAttribute("listaMedicacao", listaMedicacao);
        RequestDispatcher enviar = request.getRequestDispatcher("/CadastroMedicacao.jsp");
        enviar.forward(request, response);
    }

    protected void editar(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        request.setAttribute("codRemedio", codRemedio);
        request.setAttribute("tipoRemedio", tipoRemedio);
        request.setAttribute("valorRemedio", valorRemedio);
        request.setAttribute("nomeRemedio", nomeRemedio);
        request.setAttribute("fabricacao", fabricacao);
        request.setAttribute("desconto", desconto);
        request.setAttribute("mensagem", "Edite os dados e clique em salvar.");
        request.setAttribute("opcao", "confirmarEditar");
        encaminharParaPagina(request, response);
    }

    protected void confirmarEditar(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {

        String codRemedioStr = request.getParameter("codRemedio");
        String tipoRemedio = request.getParameter("tipoRemedio");
        String valorRemedio = request.getParameter("valorRemedio");
        String nomeRemedio = request.getParameter("nomeRemedio");
        String fabricacao = request.getParameter("fabricacao");
        String desconto = request.getParameter("desconto");

        if (codRemedioStr == null || codRemedioStr.trim().isEmpty() ||
            tipoRemedio == null || tipoRemedio.trim().isEmpty() ||
            valorRemedio == null || valorRemedio.trim().isEmpty() ||
            nomeRemedio == null || nomeRemedio.trim().isEmpty() ||
            fabricacao == null || fabricacao.trim().isEmpty() ||
            desconto == null || desconto.trim().isEmpty()) {

            request.setAttribute("mensagem", "Erro: preencha todos os campos obrigatórios.");
            request.setAttribute("opcao", "editar");

            request.setAttribute("codRemedio", codRemedioStr);
            request.setAttribute("tipoRemedio", tipoRemedio);
            request.setAttribute("valorRemedio", valorRemedio);
            request.setAttribute("nomeRemedio", nomeRemedio);
            request.setAttribute("fabricacao", fabricacao);
            request.setAttribute("desconto", desconto);

            encaminharParaPagina(request, response);
            return;
        }

        try {
            Medicacao medicacao = new Medicacao();
            medicacao.setCodRemedio(Integer.valueOf(codRemedioStr));
            medicacao.setTipoRemedio(tipoRemedio);
            medicacao.setValorRemedio(Double.valueOf(valorRemedio));
            medicacao.setNomeRemedio(nomeRemedio);
            medicacao.setFabricacao(fabricacao);
            medicacao.setDesconto(Double.valueOf(desconto));

            objMedicacaoDao.alterar(medicacao);
            encaminharParaPagina(request, response);

        } catch (NumberFormatException e) {
            request.setAttribute("mensagem", "Erro: preencha corretamente os campos numéricos (Valor do Remédio ou Desconto).");
            request.setAttribute("opcao", "editar");

            request.setAttribute("codRemedio", codRemedioStr);
            request.setAttribute("tipoRemedio", tipoRemedio);
            request.setAttribute("valorRemedio", valorRemedio);
            request.setAttribute("nomeRemedio", nomeRemedio);
            request.setAttribute("fabricacao", fabricacao);
            request.setAttribute("desconto", desconto);

            encaminharParaPagina(request, response);
        }
    }

    protected void excluir(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        request.setAttribute("codRemedio", codRemedio);
        request.setAttribute("tipoRemedio", tipoRemedio);
        request.setAttribute("valorRemedio", valorRemedio);
        request.setAttribute("nomeRemedio", nomeRemedio);
        request.setAttribute("fabricacao", fabricacao);
        request.setAttribute("desconto", desconto);
        request.setAttribute("mensagem", "Clique em salvar para excluir.");
        request.setAttribute("opcao", "confirmarExcluir");
        encaminharParaPagina(request, response);
    }

    protected void confirmarExcluir(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        Medicacao objMedicacao = new Medicacao();
        objMedicacao.setCodRemedio(Integer.valueOf(codRemedio));
        objMedicacaoDao.excluir(objMedicacao);
        encaminharParaPagina(request, response);
    }

    protected void cancelar(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        request.setAttribute("codRemedio", "0");
        request.setAttribute("tipoRemedio", "");
        request.setAttribute("valorRemedio", "0");
        request.setAttribute("nomeRemedio", "");
        request.setAttribute("fabricacao", "");
        request.setAttribute("desconto", "0");
        request.setAttribute("opcao", "cadastrar");
        encaminharParaPagina(request, response);
    }
}

