package com.mycompany.hospital.controlador;

import com.mycompany.hospital.dao.HospitalDAO;
import com.mycompany.hospital.dao.AmbulatorioDAO;
import com.mycompany.hospital.dao.entidade.Ambulatorio;
import com.mycompany.hospital.dao.entidade.Hospital;
import com.mycompany.hospital.servico.WebConstante;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(WebConstante.BASE_PATH + "/AmbulatorioControlador")
public class AmbulatorioControlador extends HttpServlet {

    private AmbulatorioDAO objAmbulatorioDao;
    private HospitalDAO objHospitalDao;

    String codAmbulatorio, horarioAtendimento, quantAmbulancias, hospitalAmbulatorio;

    @Override
    public void init() throws ServletException {
        objAmbulatorioDao = new AmbulatorioDAO();
        objHospitalDao = new HospitalDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String opcao = request.getParameter("opcao");
            if (opcao == null || opcao.isEmpty()) {
                opcao = "listar";
            }

            codAmbulatorio = request.getParameter("codAmbulatorio");
            horarioAtendimento = request.getParameter("horarioAtendimento");
            quantAmbulancias = request.getParameter("quantAmbulancias");
            hospitalAmbulatorio = request.getParameter("hospitalAmbulatorio");

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
                 case "relatorioExcel":
                    gerarRelatorioExcel(request, response);
                break;
                default:
                    throw new IllegalArgumentException("Opção inválida: " + opcao);
            }

        } catch (NumberFormatException e) {
            response.getWriter().println("Erro: um ou mais parâmetros não são numéricos válidos. " + e.getMessage());
        } catch (IllegalArgumentException e) {
            response.getWriter().println("Erro: Parâmetros ausentes ou inválidos. " + e.getMessage());
        }
    }

    protected void cadastrar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Ambulatorio objAmbulatorio = new Ambulatorio();
        objAmbulatorio.setHorarioAtendimento(horarioAtendimento);
        objAmbulatorio.setQuantAmbulancias(Integer.valueOf(quantAmbulancias));

        Hospital hospital = new Hospital();
        hospital.setCodHospital(Integer.valueOf(hospitalAmbulatorio));
        objAmbulatorio.setHospitalAmbulatorio(hospital);

        objAmbulatorioDao.salvar(objAmbulatorio);
        encaminharParaPagina(request, response);
    }

    protected void editar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("codAmbulatorio", codAmbulatorio);
        request.setAttribute("horarioAtendimento", horarioAtendimento);
        request.setAttribute("quantAmbulancias", quantAmbulancias);
        request.setAttribute("hospitalAmbulatorio", hospitalAmbulatorio);
        request.setAttribute("mensagem", "Edite os dados e clique em Salvar.");
        request.setAttribute("opcao", "confirmarEditar");
        encaminharParaPagina(request, response);
    }

    protected void confirmarEditar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Ambulatorio objAmbulatorio = new Ambulatorio();
        objAmbulatorio.setCodAmbulatorio(Integer.valueOf(codAmbulatorio));
        objAmbulatorio.setHorarioAtendimento(horarioAtendimento);
        objAmbulatorio.setQuantAmbulancias(Integer.valueOf(quantAmbulancias));

        Hospital hospital = new Hospital();
        hospital.setCodHospital(Integer.valueOf(hospitalAmbulatorio));
        objAmbulatorio.setHospitalAmbulatorio(hospital);

        objAmbulatorioDao.alterar(objAmbulatorio);
        encaminharParaPagina(request, response);
    }

    protected void excluir(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("codAmbulatorio", codAmbulatorio);
        request.setAttribute("horarioAtendimento", horarioAtendimento);
        request.setAttribute("quantAmbulancias", quantAmbulancias);
        request.setAttribute("hospitalAmbulatorio", hospitalAmbulatorio);
        request.setAttribute("mensagem", "Clique em Salvar para confirmar a exclusão.");
        request.setAttribute("opcao", "confirmarExcluir");
        encaminharParaPagina(request, response);
    }

    protected void confirmarExcluir(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Ambulatorio objAmbulatorio = new Ambulatorio();
        objAmbulatorio.setCodAmbulatorio(Integer.valueOf(codAmbulatorio));
        objAmbulatorioDao.excluir(objAmbulatorio);
        encaminharParaPagina(request, response);
    }

    protected void cancelar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("codAmbulatorio", "0");
        request.setAttribute("horarioAtendimento", "");
        request.setAttribute("quantAmbulancias", "");
        request.setAttribute("hospitalAmbulatorio", "");
        request.setAttribute("opcao", "cadastrar");
        encaminharParaPagina(request, response);
    }

    private void encaminharParaPagina(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Hospital> listaHospital = objHospitalDao.buscarTodosHospitais();
        request.setAttribute("listaHospital", listaHospital);

        List<Ambulatorio> listaAmbulatorio = objAmbulatorioDao.buscarTodosAmbulatorio();
        request.setAttribute("listaAmbulatorio", listaAmbulatorio);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/CadastroAmbulatorio.jsp");
        dispatcher.forward(request, response);
    }
    
    private void gerarRelatorioExcel(HttpServletRequest request, HttpServletResponse response) throws IOException {
    String idHospital = request.getParameter("hospitalRelatorio");

    if (idHospital == null || idHospital.isEmpty()) {
        response.getWriter().println("Selecione um hospital para gerar o relatório!");
        return;
    }

    int codHospital = Integer.parseInt(idHospital);
    List<Ambulatorio> lista = objAmbulatorioDao.buscarPorHospital(codHospital);

    response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
    response.setHeader("Content-Disposition", "attachment; filename=RelatorioAmbulatorio.xlsx");

    new com.mycompany.hospital.relatorios.RelatorioAmbulatorioExcel()
            .exportarAmbulatorios(lista, response.getOutputStream());
}
}
