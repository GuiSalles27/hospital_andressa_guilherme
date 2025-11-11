/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hospital.controlador;


import com.mycompany.hospital.dao.FuncionarioDAO;
import com.mycompany.hospital.dao.PacienteDAO;
import com.mycompany.hospital.dao.ConsultaDAO;
import com.mycompany.hospital.dao.entidade.Funcionario;
import com.mycompany.hospital.dao.entidade.Paciente;
import com.mycompany.hospital.dao.entidade.Consulta;
import com.mycompany.hospital.servico.ConverteData;
import com.mycompany.hospital.servico.WebConstante;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 *
 * @author 15114560603
 */
//@webServlet: designar uma classe com um Serviets, mapeamento da url no servidor WEB-> GlassFish, indica a cidade e faz um mapeamento. Se der erro 404 o erro é aqui
@WebServlet(WebConstante.BASE_PATH+"/ConsultaControlador")
public class ConsultaControlador extends HttpServlet {

    private ConsultaDAO objconsultaDao;
    private final ConverteData converte = new ConverteData();
    private PacienteDAO objpacienteDao;
    private FuncionarioDAO objfuncionarioDao;

    String codConsulta, diagnostico, dataConsulta, pacienteConsulta, funcionarioConsulta;

    @Override
    public void init() throws ServletException {
        objpacienteDao = new PacienteDAO();
        objfuncionarioDao = new FuncionarioDAO();
        objconsultaDao = new ConsultaDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String opcao = request.getParameter("opcao");
            if (opcao == null || opcao.isEmpty()) {
                opcao = "listar";
            }

            codConsulta = request.getParameter("codConsulta");
            diagnostico = request.getParameter("diagnostico");
            dataConsulta = request.getParameter("dataConsulta");
            pacienteConsulta = request.getParameter("pacienteConsulta");
            funcionarioConsulta = request.getParameter("funcionarioConsulta");

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
            response.getWriter().println("Erro: um ou mais parâmetros não são numéricos válidos " + e.getMessage());
        } catch (IllegalArgumentException e) {
            response.getWriter().println("Erro: Parâmetros ausentes " + e.getMessage());
        }
    }

    protected void cadastrar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Consulta objConsulta = new Consulta();
        objConsulta.setDiagnostico(diagnostico);
        objConsulta.setDataConsulta(converte.converteCalendario(dataConsulta));

        Paciente p = new Paciente();
        p.setCodPaciente(Integer.valueOf(pacienteConsulta));
        objConsulta.setPacienteConsulta(p);

        Funcionario f = new Funcionario();
        f.setCodFunc(Integer.valueOf(funcionarioConsulta));
        objConsulta.setFuncionarioConsulta(f);

        objconsultaDao.salvar(objConsulta);
        encaminharParaPagina(request, response);
    }

    protected void editar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int idConsulta = Integer.parseInt(codConsulta);
        Consulta objConsulta = objconsultaDao.buscarConsultaPorId(idConsulta);

        if (objConsulta != null) {
            request.setAttribute("codConsulta", objConsulta.getCodConsulta());
            request.setAttribute("diagnostico", objConsulta.getDiagnostico());
            request.setAttribute("dataConsulta", new SimpleDateFormat("yyyy-MM-dd").format(objConsulta.getDataConsulta().getTime()));
            request.setAttribute("pacienteConsulta", objConsulta.getPacienteConsulta().getCodPaciente());
            request.setAttribute("funcionarioConsulta", objConsulta.getFuncionarioConsulta().getCodFunc());
            request.setAttribute("mensagem", "Edite os dados e clique em salvar");
            request.setAttribute("opcao", "confirmarEditar");
        } else {
            request.setAttribute("mensagem", "Consulta não encontrada.");
            request.setAttribute("opcao", "cadastrar");
        }

        encaminharParaPagina(request, response);
    }

    protected void confirmarEditar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Consulta objConsulta = new Consulta();
        objConsulta.setCodConsulta(Integer.valueOf(codConsulta));
        objConsulta.setDiagnostico(diagnostico);
        objConsulta.setDataConsulta(converte.converteCalendario(dataConsulta));

        Paciente p = new Paciente();
        p.setCodPaciente(Integer.valueOf(pacienteConsulta));
        objConsulta.setPacienteConsulta(p);

        Funcionario f = new Funcionario();
        f.setCodFunc(Integer.valueOf(funcionarioConsulta));
        objConsulta.setFuncionarioConsulta(f);

        objconsultaDao.alterar(objConsulta);
        encaminharParaPagina(request, response);
    }

    protected void excluir(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("codConsulta", codConsulta);
        request.setAttribute("diagnostico", diagnostico);
        request.setAttribute("dataConsulta", ConverteData.convertDateFormat(dataConsulta));
        request.setAttribute("pacienteConsulta", pacienteConsulta);
        request.setAttribute("funcionarioConsulta", funcionarioConsulta);
        request.setAttribute("mensagem", "Para confirmar a exclusão, clique em salvar.");
        request.setAttribute("opcao", "confirmarExcluir");
        encaminharParaPagina(request, response);
    }

    protected void confirmarExcluir(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Consulta objConsulta = new Consulta();
        objConsulta.setCodConsulta(Integer.valueOf(codConsulta));
        objconsultaDao.excluir(objConsulta);
        encaminharParaPagina(request, response);
    }

    private void encaminharParaPagina(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Paciente> listaPaciente = objpacienteDao.buscarTodosPaciente();
        request.setAttribute("listaPaciente", listaPaciente);
        List<Funcionario> listaFuncionario = objfuncionarioDao.buscarTodosFuncionario();
        request.setAttribute("listaFuncionario", listaFuncionario);
        List<Consulta> listaConsulta = objconsultaDao.buscarTodasConsultas();
        request.setAttribute("listaConsulta", listaConsulta);

        RequestDispatcher enviar = request.getRequestDispatcher("/CadastroConsulta.jsp");
        enviar.forward(request, response);
    }

    protected void cancelar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("codConsulta", "0");
        request.setAttribute("diagnostico", "");
        request.setAttribute("dataConsulta", "");
        request.setAttribute("pacienteConsulta", "");
        request.setAttribute("funcionarioConsulta", "");
        request.setAttribute("opcao", "cadastrar");
        encaminharParaPagina(request, response);
    }
}

