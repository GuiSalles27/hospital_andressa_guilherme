package com.mycompany.hospital.controlador;

import com.mycompany.hospital.dao.FuncionarioDAO;
import com.mycompany.hospital.dao.PacienteDAO;
import com.mycompany.hospital.dao.InternacaoDAO;
import com.mycompany.hospital.dao.entidade.Funcionario;
import com.mycompany.hospital.dao.entidade.Paciente;
import com.mycompany.hospital.dao.entidade.Internacao;
import com.mycompany.hospital.servico.WebConstante;
import com.mycompany.hospital.relatorios.RelatorioInternacaoExcel;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(WebConstante.BASE_PATH+"/InternacaoControlador")
public class InternacaoControlador extends HttpServlet {
    
    private Internacao objInternacao;
    private InternacaoDAO objinternacaoDao;
    private Paciente objPaciente;
    private PacienteDAO objpacienteDao;
    private Funcionario objFuncionario;
    private FuncionarioDAO objfuncionarioDao;
    String codInternacao, dataHora, sala, descricao, pacienteInternacao, funcionarioInternacao;

    @Override
    public void init() throws ServletException {
        objpacienteDao = new PacienteDAO();
        objPaciente = new Paciente();
        objfuncionarioDao = new FuncionarioDAO();
        objFuncionario = new Funcionario();
        objInternacao = new Internacao();
        objinternacaoDao = new InternacaoDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            String opcao = request.getParameter("opcao");
            if(opcao==null||opcao.isEmpty()){
                opcao="listar";
            }
            codInternacao = request.getParameter("codInternacao");
            dataHora = request.getParameter("dataHora");
            sala = request.getParameter("sala");
            descricao = request.getParameter("descricao");
            pacienteInternacao = request.getParameter("pacienteInternacao");
            funcionarioInternacao = request.getParameter("funcionarioInternacao");
            
            switch (opcao){
                case "listar":
                    encaminharParaPagina(request, response);
                    break;
                case "cadastrar":
                   cadastrar(request,response);
                    break;
                case "editar":
                    editar(request,response);
                    break;
                case "confirmarEditar":
                    confirmarEditar(request,response);
                    break;
                case "excluir":
                    excluir(request,response);
                    break;
                case "confirmarExcluir":
                    confirmarExcluir(request,response);
                    break;
                case "cancelar":
                    cancelar(request,response);
                    break;
                case "exportarExcel":
                    exportarExcel(request, response);
                    break;
                default:
                    throw new IllegalArgumentException("Opção inválida "+opcao);
            }      
        }catch(Exception e){ 
            response.getWriter().println("Erro: "+e.getMessage());
        }
    }
    
    protected void cadastrar(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException { 
    Internacao novaInternacao = new Internacao();
    novaInternacao.setDataHora(dataHora);
    novaInternacao.setSala(Integer.valueOf(sala));
    novaInternacao.setDescricao(descricao);

    // Criar e setar paciente
    Paciente paciente = new Paciente();
    paciente.setCodPaciente(Integer.valueOf(pacienteInternacao));
    novaInternacao.setPacienteInternacao(paciente);

    // Criar e setar funcionário
    Funcionario funcionario = new Funcionario();
    funcionario.setCodFunc(Integer.valueOf(funcionarioInternacao));
    novaInternacao.setFuncionarioInternacao(funcionario);

    objinternacaoDao.salvar(novaInternacao);
    encaminharParaPagina(request, response);
}

    
    protected void editar(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException{ 
        request.setAttribute("codInternacao", codInternacao);
        request.setAttribute("dataHora", dataHora);
        request.setAttribute("sala", sala);
        request.setAttribute("descricao", descricao);
        request.setAttribute("pacienteInternacao", pacienteInternacao);
        request.setAttribute("funcionarioInternacao", funcionarioInternacao);
        request.setAttribute("mensagem", "Edite os dados e clique em salvar");
        request.setAttribute("opcao", "confirmarEditar");
        encaminharParaPagina(request, response);
    } 
    
    protected void confirmarEditar(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException { 
    Internacao internacaoEditada = new Internacao();
    internacaoEditada.setCodInternacao(Integer.valueOf(codInternacao));
    internacaoEditada.setDataHora(dataHora);
    internacaoEditada.setSala(Integer.valueOf(sala));
    internacaoEditada.setDescricao(descricao);

    Paciente paciente = new Paciente();
    paciente.setCodPaciente(Integer.valueOf(pacienteInternacao));
    internacaoEditada.setPacienteInternacao(paciente);

    Funcionario funcionario = new Funcionario();
    funcionario.setCodFunc(Integer.valueOf(funcionarioInternacao));
    internacaoEditada.setFuncionarioInternacao(funcionario);

    objinternacaoDao.alterar(internacaoEditada);
    encaminharParaPagina(request, response);
}

    protected void excluir (HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException{ 
        request.setAttribute("codInternacao", codInternacao);
        request.setAttribute("dataHora", dataHora);
        request.setAttribute("sala", sala);
        request.setAttribute("descricao", descricao);
        request.setAttribute("pacienteInternacao", pacienteInternacao);
        request.setAttribute("funcionarioInternacao", funcionarioInternacao);
        request.setAttribute("mensagem", "Para realizar a exclusão, clique em salvar.");
        request.setAttribute("opcao", "confirmarExcluir");
        encaminharParaPagina(request, response);
    } 
    
    protected void confirmarExcluir(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException{ 
        objInternacao.setCodInternacao(Integer.valueOf(codInternacao));
        objinternacaoDao.excluir(objInternacao);
        encaminharParaPagina(request, response);
    }
    
    private void encaminharParaPagina(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException{
        List<Paciente> listaPaciente = objpacienteDao.buscarTodosPaciente();
        request.setAttribute("listaPaciente", listaPaciente);
        List<Funcionario> listaFuncionario = objfuncionarioDao.buscarTodosFuncionario();
        request.setAttribute("listaFuncionario", listaFuncionario);
        List<Internacao> listaInternacao = objinternacaoDao.buscarTodasInternacoes();
        request.setAttribute("listaInternacao", listaInternacao);
        List<Integer> listaSalas = objinternacaoDao.buscarSalasAtivas();
        request.setAttribute("listaSalas", listaSalas);
        
        RequestDispatcher enviar = request.getRequestDispatcher("/CadastroInternacao.jsp");
        enviar.forward(request, response);
    }
    
    protected void cancelar(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException{
        request.setAttribute("codInternacao", "0");
        request.setAttribute("dataHora", "");
        request.setAttribute("sala", "0");
        request.setAttribute("descricao", "");
        request.setAttribute("pacienteInternacao", "");
        request.setAttribute("funcionarioInternacao", "");
        request.setAttribute("opcao", "cadastrar");
        encaminharParaPagina(request, response);
    }

    // 🔹 Exportar Excel
    private void exportarExcel(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String salaParam = request.getParameter("salaFiltro");
        List<Internacao> listaFiltrada =
                (salaParam != null && !salaParam.isEmpty())
                        ? objinternacaoDao.buscarInternacoesPorSala(Integer.parseInt(salaParam))
                        : objinternacaoDao.buscarTodasInternacoes();

        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=internacoes.xlsx");

        RelatorioInternacaoExcel exporter = new RelatorioInternacaoExcel();
        exporter.exportarInternacoes(listaFiltrada, response.getOutputStream());
    }
}
