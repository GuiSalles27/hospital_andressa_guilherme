package com.mycompany.hospital.controlador;

import com.mycompany.hospital.dao.CargoDAO;
import com.mycompany.hospital.dao.entidade.Cargo;
import com.mycompany.hospital.servico.WebConstante;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(WebConstante.BASE_PATH + "/CargoControlador")
public class CargoControlador extends HttpServlet {

    private Cargo objCargo;
    private CargoDAO objcargoDao;

    @Override
    public void init() throws ServletException {
        objcargoDao = new CargoDAO();
        objCargo = new Cargo();
    }

    // ✅ Novo: POST chama GET para reaproveitar o switch
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String opcao = request.getParameter("opcao");
            if (opcao == null || opcao.isEmpty()) {
                opcao = "cadastrar";
            }

            switch (opcao) {
                case "listar":
                    encaminharParaPagina(request, response);
                    break;
                case "cadastrar":
                    cadastrar(request, response);
                    break;
                case "editar":
                    editar(request, response);
                    break;
                case "confirmarEditar":
                    confirmarEditar(request, response);
                    break;
                case "excluir":
                    excluir(request, response);
                    break;
                case "confirmarExcluir":
                    confirmarExcluir(request, response);
                    break;
                case "cancelar":
                    cancelar(request, response);
                    break;
                case "relatorioCargo":
                    relatorioCargo(request, response);
                    break;
                default:
                    throw new IllegalArgumentException("Opção inválida " + opcao);
            }

        } catch (NumberFormatException e) {
            response.getWriter().println("Erro: um ou mais parâmetros não são numéricos válidos " + e.getMessage());
        } catch (IllegalArgumentException e) {
            response.getWriter().println("Erro: Parâmetros ausentes " + e.getMessage());
        }
    }

    protected void cadastrar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String nomeCargo = request.getParameter("nomeCargo");
        String salarioInicial = request.getParameter("salarioInicial");
        String descricao = request.getParameter("descricao");
        String bonificacao = request.getParameter("bonificacao");

        if (nomeCargo == null || nomeCargo.trim().isEmpty()
                || salarioInicial == null || salarioInicial.trim().isEmpty()
                || descricao == null || descricao.trim().isEmpty()
                || bonificacao == null || bonificacao.trim().isEmpty()) {

            request.setAttribute("mensagem", "Erro: preencha todos os campos obrigatórios.");
            request.setAttribute("codCargo", request.getParameter("codCargo"));
            request.setAttribute("nomeCargo", nomeCargo);
            request.setAttribute("salarioInicial", salarioInicial);
            request.setAttribute("descricao", descricao);
            request.setAttribute("bonificacao", bonificacao);
            request.setAttribute("opcao", "cadastrar");
            encaminharParaPagina(request, response);
            return;
        }

        try {
            Cargo novoCargo = new Cargo();
            novoCargo.setNomeCargo(nomeCargo);
            novoCargo.setSalarioInicial(Double.parseDouble(salarioInicial));
            novoCargo.setDescricao(descricao);
            novoCargo.setBonificacao(Double.parseDouble(bonificacao));

            objcargoDao.salvar(novoCargo);
            encaminharParaPagina(request, response);

        } catch (NumberFormatException e) {
            request.setAttribute("mensagem", "Erro: preencha corretamente os campos numéricos (salário e bonificação).");
            request.setAttribute("codCargo", request.getParameter("codCargo"));
            request.setAttribute("nomeCargo", nomeCargo);
            request.setAttribute("salarioInicial", salarioInicial);
            request.setAttribute("descricao", descricao);
            request.setAttribute("bonificacao", bonificacao);
            request.setAttribute("opcao", "cadastrar");
            encaminharParaPagina(request, response);
        }
    }

    protected void editar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("codCargo", request.getParameter("codCargo"));
        request.setAttribute("nomeCargo", request.getParameter("nomeCargo"));
        request.setAttribute("salarioInicial", request.getParameter("salarioInicial"));
        request.setAttribute("descricao", request.getParameter("descricao"));
        request.setAttribute("bonificacao", request.getParameter("bonificacao"));
        request.setAttribute("mensagem", "Edite os dados e clique em salvar");
        request.setAttribute("opcao", "confirmarEditar");
        encaminharParaPagina(request, response);
    }

    protected void confirmarEditar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String codCargoStr = request.getParameter("codCargo");
        String nomeCargo = request.getParameter("nomeCargo");
        String salarioInicial = request.getParameter("salarioInicial");
        String descricao = request.getParameter("descricao");
        String bonificacao = request.getParameter("bonificacao");

        if (codCargoStr == null || codCargoStr.trim().isEmpty()
                || nomeCargo == null || nomeCargo.trim().isEmpty()
                || salarioInicial == null || salarioInicial.trim().isEmpty()
                || descricao == null || descricao.trim().isEmpty()
                || bonificacao == null || bonificacao.trim().isEmpty()) {

            request.setAttribute("mensagem", "Erro: preencha todos os campos obrigatórios.");
            request.setAttribute("opcao", "editar");

            request.setAttribute("codCargo", codCargoStr);
            request.setAttribute("nomeCargo", nomeCargo);
            request.setAttribute("salarioInicial", salarioInicial);
            request.setAttribute("descricao", descricao);
            request.setAttribute("bonificacao", bonificacao);

            encaminharParaPagina(request, response);
            return;
        }

        try {
            Cargo cargo = new Cargo();
            cargo.setCodCargo(Integer.parseInt(codCargoStr));
            cargo.setNomeCargo(nomeCargo);
            cargo.setSalarioInicial(Double.parseDouble(salarioInicial));
            cargo.setDescricao(descricao);
            cargo.setBonificacao(Double.parseDouble(bonificacao));

            objcargoDao.alterar(cargo);
            encaminharParaPagina(request, response);

        } catch (NumberFormatException e) {
            request.setAttribute("mensagem", "Erro: preencha corretamente os campos numéricos (código, salário ou bonificação).");
            request.setAttribute("opcao", "editar");

            request.setAttribute("codCargo", codCargoStr);
            request.setAttribute("nomeCargo", nomeCargo);
            request.setAttribute("salarioInicial", salarioInicial);
            request.setAttribute("descricao", descricao);
            request.setAttribute("bonificacao", bonificacao);

            encaminharParaPagina(request, response);
        }
    }

    protected void excluir(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("codCargo", request.getParameter("codCargo"));
        request.setAttribute("nomeCargo", request.getParameter("nomeCargo"));
        request.setAttribute("salarioInicial", request.getParameter("salarioInicial"));
        request.setAttribute("descricao", request.getParameter("descricao"));
        request.setAttribute("bonificacao", request.getParameter("bonificacao"));
        request.setAttribute("mensagem", "Clique em salvar para excluir");
        request.setAttribute("opcao", "confirmarExcluir");
        encaminharParaPagina(request, response);
    }

    protected void confirmarExcluir(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String codCargo = request.getParameter("codCargo");
        objCargo.setCodCargo(Integer.valueOf(codCargo));
        objcargoDao.excluir(objCargo);
        encaminharParaPagina(request, response);
    }

    private void encaminharParaPagina(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Cargo> listaCargo = objcargoDao.buscarTodosCargos();
        request.setAttribute("listaCargo", listaCargo);
        RequestDispatcher enviar = request.getRequestDispatcher("/CadastroCargo.jsp");
        enviar.forward(request, response);
    }

    protected void cancelar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("codCargo", "0");
        request.setAttribute("nomeCargo", "");
        request.setAttribute("salarioInicial", "");
        request.setAttribute("descricao", "");
        request.setAttribute("bonificacao", "");
        request.setAttribute("opcao", "cadastrar");
        encaminharParaPagina(request, response);
    }

    protected void relatorioCargo(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String sMin = request.getParameter("salarioMin");
            String sMax = request.getParameter("salarioMax");
            Double salarioMin = sMin != null && !sMin.isEmpty() ? Double.valueOf(sMin) : null;
            Double salarioMax = sMax != null && !sMax.isEmpty() ? Double.valueOf(sMax) : null;

            com.mycompany.hospital.relatorios.RelatorioCargoExcel relatorio
                    = new com.mycompany.hospital.relatorios.RelatorioCargoExcel();
            relatorio.gerarRelatorio(salarioMin, salarioMax, (jakarta.servlet.http.HttpServletResponse) response);

        } catch (NumberFormatException e) {
            request.setAttribute("mensagem", "Erro: valores inválidos para a faixa salarial.");
            encaminharParaPagina(request, response);
        }
    }

}
