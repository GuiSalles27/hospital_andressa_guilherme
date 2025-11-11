package com.mycompany.hospital.controlador;

import com.mycompany.hospital.dao.PlanoDAO;
import com.mycompany.hospital.dao.entidade.Plano;
import com.mycompany.hospital.servico.WebConstante;
import com.mycompany.hospital.relatorios.RelatorioPlanoExcel;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(WebConstante.BASE_PATH + "/PlanoControlador")
public class PlanoControlador extends HttpServlet {

    private Plano objPlano;
    private PlanoDAO objPlanoDao;
    String codPlano, nomePlano, tipoPlano, valorPlano;

    @Override
    public void init() throws ServletException {
        objPlanoDao = new PlanoDAO();
        objPlano = new Plano();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String opcao = request.getParameter("opcao");
            if (opcao == null || opcao.isEmpty()) {
                opcao = "listar";
            }

            codPlano = request.getParameter("codPlano");
            nomePlano = request.getParameter("nomePlano");
            tipoPlano = request.getParameter("tipoPlano");
            valorPlano = request.getParameter("valorPlano");

            switch (opcao) {
                case "listar":
                    // usa tipoPlano somente para filtro real
                    encaminharParaPagina(request, response, tipoPlano);
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
                    throw new IllegalArgumentException("Opção inválida " + opcao);
            }
        } catch (Exception e) {
            response.getWriter().println("Erro: " + e.getMessage());
        }
    }

    // Cadastrar
    protected void cadastrar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (nomePlano == null || nomePlano.trim().isEmpty()
                || valorPlano == null || valorPlano.trim().isEmpty()
                || tipoPlano == null || tipoPlano.trim().isEmpty()) {
            request.setAttribute("mensagem", "Erro: preencha todos os campos obrigatórios.");
            request.setAttribute("codPlano", codPlano);
            request.setAttribute("nomePlano", nomePlano);
            request.setAttribute("tipoPlano", tipoPlano);
            request.setAttribute("valorPlano", valorPlano);
            request.setAttribute("opcao", "cadastrar");
            encaminharParaPagina(request, response, null);
            return;
        }

        try {
            objPlano.setNomePlano(nomePlano);
            objPlano.setValorPlano(Double.parseDouble(valorPlano));
            objPlano.setTipoPlano(tipoPlano);
            objPlanoDao.salvar(objPlano);
            encaminharParaPagina(request, response, null);
        } catch (NumberFormatException e) {
            request.setAttribute("mensagem", "Erro: valor inválido.");
            encaminharParaPagina(request, response, null);
        }
    }

    // Encaminhar para a página de listagem
    private void encaminharParaPagina(HttpServletRequest request,
                                      HttpServletResponse response,
                                      String filtroTipo)
            throws ServletException, IOException {

        List<Plano> listaPlano = (filtroTipo != null && !filtroTipo.isEmpty())
                ? objPlanoDao.buscarPlanosPorTipo(filtroTipo)
                : objPlanoDao.buscarTodosPlanos();

        List<String> listaTipos = objPlanoDao.buscarTiposPlano();
        request.setAttribute("listaTipos", listaTipos);
        request.setAttribute("filtroTipo", filtroTipo == null ? "" : filtroTipo);
        request.setAttribute("listaPlano", listaPlano);

        if (request.getAttribute("opcao") == null) {
            request.setAttribute("opcao", "cadastrar");
        }

        RequestDispatcher enviar = request.getRequestDispatcher("/CadastroPlano.jsp");
        enviar.forward(request, response);
    }

    // Editar
    protected void editar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("codPlano", codPlano);
        request.setAttribute("nomePlano", nomePlano);
        request.setAttribute("tipoPlano", tipoPlano);
        request.setAttribute("valorPlano", valorPlano);
        request.setAttribute("mensagem", "Edite os dados e clique em salvar.");
        request.setAttribute("opcao", "confirmarEditar");
        // após clicar em editar, mostrar todos os planos
        encaminharParaPagina(request, response, null);
    }

    // Confirmar edição
    protected void confirmarEditar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Plano plano = new Plano();
            plano.setCodPlano(Integer.valueOf(request.getParameter("codPlano")));
            plano.setNomePlano(request.getParameter("nomePlano"));
            plano.setTipoPlano(request.getParameter("tipoPlano"));
            plano.setValorPlano(Double.valueOf(request.getParameter("valorPlano")));
            objPlanoDao.alterar(plano);
            encaminharParaPagina(request, response, null);
        } catch (NumberFormatException e) {
            request.setAttribute("mensagem", "Erro: valor inválido.");
            encaminharParaPagina(request, response, null);
        }
    }

    // Excluir
    protected void excluir(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("codPlano", codPlano);
        request.setAttribute("nomePlano", nomePlano);
        request.setAttribute("tipoPlano", tipoPlano);
        request.setAttribute("valorPlano", valorPlano);
        request.setAttribute("mensagem", "Clique em salvar para excluir.");
        request.setAttribute("opcao", "confirmarExcluir");
        // após preparar exclusão, mostrar todos os planos
        encaminharParaPagina(request, response, null);
    }

    // Confirmar exclusão
    protected void confirmarExcluir(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        objPlano.setCodPlano(Integer.valueOf(codPlano));
        objPlanoDao.excluir(objPlano);
        encaminharParaPagina(request, response, null);
    }

    // Cancelar
    protected void cancelar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("codPlano", "0");
        request.setAttribute("nomePlano", "");
        request.setAttribute("tipoPlano", "");
        request.setAttribute("valorPlano", "0");
        request.setAttribute("opcao", "cadastrar");
        encaminharParaPagina(request, response, null);
    }

    // Exportar Excel (aqui sim usa o filtro, se houver)
    private void exportarExcel(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String tipo = request.getParameter("tipoPlano");
        List<Plano> listaFiltrada =
                (tipo != null && !tipo.isEmpty())
                        ? objPlanoDao.buscarPlanosPorTipo(tipo)
                        : objPlanoDao.buscarTodosPlanos();

        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=planos.xlsx");

        RelatorioPlanoExcel exporter = new RelatorioPlanoExcel();
        exporter.exportarPlanos(listaFiltrada, response.getOutputStream());
    }
}
