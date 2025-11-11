/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hospital.controlador;


import com.mycompany.hospital.dao.CargoDAO;
import com.mycompany.hospital.dao.HospitalDAO;
import com.mycompany.hospital.dao.FuncionarioDAO;
import com.mycompany.hospital.dao.entidade.Cargo;
import com.mycompany.hospital.dao.entidade.Hospital;
import com.mycompany.hospital.dao.entidade.Funcionario;
import com.mycompany.hospital.servico.ConverteData;
import com.mycompany.hospital.servico.WebConstante;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author 15114560603
 */
//@webServlet: designar uma classe com um Serviets, mapeamento da url no servidor WEB-> GlassFish, indica a cidade e faz um mapeamento. Se der erro 404 o erro é aqui
@WebServlet(WebConstante.BASE_PATH + "/FuncionarioControlador")
public class FuncionarioControlador extends HttpServlet {

    private FuncionarioDAO objFuncionarioDao;
    private CargoDAO objcargoDao;
    private HospitalDAO objhospitalDao;
    private final ConverteData converte = new ConverteData();

    @Override
    public void init() throws ServletException {
        objcargoDao = new CargoDAO();
        objhospitalDao = new HospitalDAO();
        objFuncionarioDao = new FuncionarioDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String opcao = request.getParameter("opcao");
            if (opcao == null || opcao.isEmpty()) {
                opcao = "listar";
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
                default:
                    throw new IllegalArgumentException("Opção inválida " + opcao);
            }
        } catch (NumberFormatException e) {
            response.getWriter().println("Erro: um ou mais parâmetros não são numéricos válidos. " + e.getMessage());
        } catch (IllegalArgumentException e) {
            response.getWriter().println("Erro: Parâmetros ausentes. " + e.getMessage());
        }
    }

    protected void cadastrar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String nomeFunc = request.getParameter("nomeFunc");
        String cpf = request.getParameter("cpf");
        String rg = request.getParameter("rg");
        String numRegistro = request.getParameter("numRegistro");
        String dataAdmissao = request.getParameter("dataAdmissao");
        String horarioTrabalho = request.getParameter("horarioTrabalho");
        String cargoFuncionario = request.getParameter("cargoFuncionario");
        String hospitalFuncionario = request.getParameter("hospitalFuncionario");

        // validação
        if (nomeFunc == null || nomeFunc.isEmpty() ||
            cpf == null || cpf.isEmpty() ||
            rg == null || rg.isEmpty() ||
            numRegistro == null || numRegistro.isEmpty() ||
            dataAdmissao == null || dataAdmissao.isEmpty() ||
            horarioTrabalho == null || horarioTrabalho.isEmpty() ||
            cargoFuncionario == null || cargoFuncionario.isEmpty() ||
            hospitalFuncionario == null || hospitalFuncionario.isEmpty()) {

            request.setAttribute("mensagem", "Erro: preencha todos os campos obrigatórios.");
            request.setAttribute("opcao", "cadastrar");
            encaminharParaPagina(request, response);
            return;
        }

        Funcionario f = new Funcionario();
        f.setNomeFunc(nomeFunc);
        f.setCpf(cpf);
        f.setRg(rg);
        f.setNumRegistro(numRegistro);
        f.setDataAdmissao(converte.converteCalendario(dataAdmissao));
        f.setHorarioTrabalho(horarioTrabalho);

        Cargo cargo = new Cargo();
        cargo.setCodCargo(Integer.valueOf(cargoFuncionario));
        f.setCargoFuncionario(cargo);

        Hospital hospital = new Hospital();
        hospital.setCodHospital(Integer.valueOf(hospitalFuncionario));
        f.setHospitalFuncionario(hospital);

        objFuncionarioDao.salvar(f);
        encaminharParaPagina(request, response);
    }

    protected void editar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setAttribute("codFunc", request.getParameter("codFunc"));
        request.setAttribute("nomeFunc", request.getParameter("nomeFunc"));
        request.setAttribute("cpf", request.getParameter("cpf"));
        request.setAttribute("rg", request.getParameter("rg")); // corrigido
        request.setAttribute("numRegistro", request.getParameter("numRegistro"));
        request.setAttribute("dataAdmissao", ConverteData.convertDateFormat(request.getParameter("dataAdmissao")));
        request.setAttribute("horarioTrabalho", request.getParameter("horarioTrabalho"));
        request.setAttribute("cargoFuncionario", request.getParameter("cargoFuncionario"));
        request.setAttribute("hospitalFuncionario", request.getParameter("hospitalFuncionario"));
        request.setAttribute("mensagem", "Edite os dados e clique em salvar.");
        request.setAttribute("opcao", "confirmarEditar");
        encaminharParaPagina(request, response);
    }

    protected void confirmarEditar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String codFunc = request.getParameter("codFunc");

        Funcionario f = new Funcionario();
        f.setCodFunc(Integer.valueOf(codFunc));
        f.setNomeFunc(request.getParameter("nomeFunc"));
        f.setCpf(request.getParameter("cpf"));
        f.setRg(request.getParameter("rg"));
        f.setNumRegistro(request.getParameter("numRegistro"));
        f.setDataAdmissao(converte.converteCalendario(request.getParameter("dataAdmissao")));
        f.setHorarioTrabalho(request.getParameter("horarioTrabalho"));

        Cargo cargo = new Cargo();
        cargo.setCodCargo(Integer.valueOf(request.getParameter("cargoFuncionario")));
        f.setCargoFuncionario(cargo);

        Hospital hospital = new Hospital();
        hospital.setCodHospital(Integer.valueOf(request.getParameter("hospitalFuncionario")));
        f.setHospitalFuncionario(hospital);

        objFuncionarioDao.alterar(f);
        encaminharParaPagina(request, response);
    }

    protected void excluir(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setAttribute("codFunc", request.getParameter("codFunc"));
        request.setAttribute("nomeFunc", request.getParameter("nomeFunc"));
        request.setAttribute("cpf", request.getParameter("cpf"));
        request.setAttribute("rg", request.getParameter("rg")); // corrigido
        request.setAttribute("numRegistro", request.getParameter("numRegistro"));
        request.setAttribute("dataAdmissao", ConverteData.convertDateFormat(request.getParameter("dataAdmissao")));
        request.setAttribute("horarioTrabalho", request.getParameter("horarioTrabalho"));
        request.setAttribute("cargoFuncionario", request.getParameter("cargoFuncionario"));
        request.setAttribute("hospitalFuncionario", request.getParameter("hospitalFuncionario"));
        request.setAttribute("mensagem", "Clique em salvar para confirmar a exclusão do funcionário.");
        request.setAttribute("opcao", "confirmarExcluir");
        encaminharParaPagina(request, response);
    }

    protected void confirmarExcluir(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String codFunc = request.getParameter("codFunc");
        Funcionario f = new Funcionario();
        f.setCodFunc(Integer.valueOf(codFunc));
        objFuncionarioDao.excluir(f);
        encaminharParaPagina(request, response);
    }

    private void encaminharParaPagina(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Cargo> listaCargo = objcargoDao.buscarTodosCargos();
        request.setAttribute("listaCargo", listaCargo);

        List<Hospital> listaHospital = objhospitalDao.buscarTodosHospitais();
        request.setAttribute("listaHospital", listaHospital);

        List<Funcionario> listaFuncionario = objFuncionarioDao.buscarTodosFuncionario();
        request.setAttribute("listaFuncionario", listaFuncionario);

        RequestDispatcher enviar = request.getRequestDispatcher("/CadastroFuncionario.jsp");
        enviar.forward(request, response);
    }

    protected void cancelar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("codFunc", "0");
        request.setAttribute("nomeFunc", "");
        request.setAttribute("cpf", "");
        request.setAttribute("rg", "");
        request.setAttribute("numRegistro", "");
        request.setAttribute("dataAdmissao", "");
        request.setAttribute("horarioTrabalho", "");
        request.setAttribute("cargoFuncionario", "");
        request.setAttribute("hospitalFuncionario", "");
        request.setAttribute("opcao", "cadastrar");
        encaminharParaPagina(request, response);
    }
}


