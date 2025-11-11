package com.mycompany.hospital.controlador;

import com.mycompany.hospital.dao.FuncionarioDAO;
import com.mycompany.hospital.dao.PacienteDAO;
import com.mycompany.hospital.dao.ExameDAO;
import com.mycompany.hospital.dao.entidade.Funcionario;
import com.mycompany.hospital.dao.entidade.Paciente;
import com.mycompany.hospital.dao.entidade.Exame;
import com.mycompany.hospital.relatorios.RelatorioExameExcel;
import com.mycompany.hospital.servico.WebConstante;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(WebConstante.BASE_PATH + "/ExameControlador")
public class ExameControlador extends HttpServlet {

    private ExameDAO objExameDao;
    private PacienteDAO objPacienteDao;
    private FuncionarioDAO objFuncionarioDao;

    String codExame, tipoExame, valor, resultado, pacienteExame, funcionarioExame;

    @Override
    public void init() throws ServletException {
        objExameDao = new ExameDAO();
        objPacienteDao = new PacienteDAO();
        objFuncionarioDao = new FuncionarioDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String opcao = request.getParameter("opcao");
            if (opcao == null || opcao.isEmpty()) {
                opcao = "listar";
            }

            codExame = request.getParameter("codExame");
            tipoExame = request.getParameter("tipoExame");
            valor = request.getParameter("valor");
            resultado = request.getParameter("resultado");
            pacienteExame = request.getParameter("pacienteExame");
            funcionarioExame = request.getParameter("funcionarioExame");

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
                case "exportarExcel":
                    exportarExcel(request, response);
                    break;
                default:
                    throw new IllegalArgumentException("Opção inválida: " + opcao);
            }

        } catch (Exception e) {
            response.getWriter().println("Erro: " + e.getMessage());
        }
    }

    protected void cadastrar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Exame objExame = new Exame();
        objExame.setTipoExame(tipoExame);
        objExame.setValor(Double.valueOf(valor));
        objExame.setResultado(resultado);

        Paciente paciente = new Paciente();
        paciente.setCodPaciente(Integer.valueOf(pacienteExame));
        objExame.setPacienteExame(paciente);

        Funcionario funcionario = new Funcionario();
        funcionario.setCodFunc(Integer.valueOf(funcionarioExame));
        objExame.setFuncionarioExame(funcionario);

        objExameDao.salvar(objExame);
        encaminharParaPagina(request, response);
    }

    protected void editar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("codExame", codExame);
        request.setAttribute("tipoExame", tipoExame);
        request.setAttribute("valor", valor);
        request.setAttribute("resultado", resultado);
        request.setAttribute("pacienteExame", pacienteExame);
        request.setAttribute("funcionarioExame", funcionarioExame);
        request.setAttribute("mensagem", "Edite os dados e clique em Salvar.");
        request.setAttribute("opcao", "confirmarEditar");
        encaminharParaPagina(request, response);
    }

    protected void confirmarEditar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Exame objExame = new Exame();
        objExame.setCodExame(Integer.valueOf(codExame));
        objExame.setTipoExame(tipoExame);
        objExame.setValor(Double.valueOf(valor));
        objExame.setResultado(resultado);

        Paciente paciente = new Paciente();
        paciente.setCodPaciente(Integer.valueOf(pacienteExame));
        objExame.setPacienteExame(paciente);

        Funcionario funcionario = new Funcionario();
        funcionario.setCodFunc(Integer.valueOf(funcionarioExame));
        objExame.setFuncionarioExame(funcionario);

        objExameDao.alterar(objExame);
        encaminharParaPagina(request, response);
    }

    protected void excluir(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("codExame", codExame);
        request.setAttribute("tipoExame", tipoExame);
        request.setAttribute("valor", valor);
        request.setAttribute("resultado", resultado);
        request.setAttribute("pacienteExame", pacienteExame);
        request.setAttribute("funcionarioExame", funcionarioExame);
        request.setAttribute("mensagem", "Clique em Salvar para confirmar a exclusão.");
        request.setAttribute("opcao", "confirmarExcluir");
        encaminharParaPagina(request, response);
    }

    protected void confirmarExcluir(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Exame objExame = new Exame();
        objExame.setCodExame(Integer.valueOf(codExame));
        objExameDao.excluir(objExame);
        encaminharParaPagina(request, response);
    }

    protected void cancelar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("codExame", "0");
        request.setAttribute("tipoExame", "");
        request.setAttribute("valor", "");
        request.setAttribute("resultado", "");
        request.setAttribute("pacienteExame", "");
        request.setAttribute("funcionarioExame", "");
        request.setAttribute("opcao", "cadastrar");
        encaminharParaPagina(request, response);
    }

    private void encaminharParaPagina(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Paciente> listaPaciente = objPacienteDao.buscarTodosPaciente();
        request.setAttribute("listaPaciente", listaPaciente);

        List<Funcionario> listaFuncionario = objFuncionarioDao.buscarTodosFuncionario();
        request.setAttribute("listaFuncionario", listaFuncionario);

        List<Exame> listaExame = objExameDao.buscarTodosExame();
        request.setAttribute("listaExame", listaExame);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/CadastroExame.jsp");
        dispatcher.forward(request, response);
    }

    // 🔹 Novo método para exportar Excel
    private void exportarExcel(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String[] selecionados = request.getParameterValues("pacientesSelecionados");

        List<Exame> listaExames;
        if (selecionados != null && selecionados.length > 0) {
            List<Integer> ids = Arrays.stream(selecionados)
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
            listaExames = objExameDao.buscarExamesPorPacientes(ids);
        } else {
            listaExames = objExameDao.buscarTodosExame();
        }

        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=exames.xlsx");

        RelatorioExameExcel exporter = new RelatorioExameExcel();
        exporter.exportarExames(listaExames, response.getOutputStream());
    }
}
