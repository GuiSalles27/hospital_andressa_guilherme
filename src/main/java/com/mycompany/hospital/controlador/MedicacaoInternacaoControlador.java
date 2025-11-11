package com.mycompany.hospital.controlador;

import com.mycompany.hospital.dao.InternacaoDAO;
import com.mycompany.hospital.dao.MedicacaoDAO;
import com.mycompany.hospital.dao.MedicacaoInternacaoDAO;
import com.mycompany.hospital.dao.entidade.Internacao;
import com.mycompany.hospital.dao.entidade.Medicacao;
import com.mycompany.hospital.dao.entidade.MedicacaoInternacao;
import com.mycompany.hospital.servico.WebConstante;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(WebConstante.BASE_PATH + "/MedicacaoInternacaoControlador")
public class MedicacaoInternacaoControlador extends HttpServlet {

    private MedicacaoInternacaoDAO dao;
    private MedicacaoDAO medicacaoDAO;
    private InternacaoDAO internacaoDAO;

    @Override
    public void init() throws ServletException {
        dao = new MedicacaoInternacaoDAO();
        medicacaoDAO = new MedicacaoDAO();
        internacaoDAO = new InternacaoDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String opcao = request.getParameter("opcao");
        if (opcao == null) {
            listar(request, response);
        } else {
            switch (opcao) {
                case "listar":
                    listar(request, response);
                    break;
                case "removerCarrinho":
                    removerDoCarrinho(request, response);
                    break;
                case "cancelarVenda":
                    cancelarVenda(request, response);
                    break;
                case "finalizarVenda":
                    finalizarVenda(request, response);
                    break;
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String opcao = request.getParameter("opcao");
        switch (opcao) {
            case "iniciarVenda":
                iniciarVenda(request, response);
                break;
            case "adicionarCarrinho":
                adicionarAoCarrinho(request, response);
                break;
        }
    }

    private void listar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("listaInternacoes", internacaoDAO.buscarTodasInternacoes());
        request.setAttribute("listaMedicamentos", medicacaoDAO.buscarTodasMedicacoes());
        encaminharParaPagina(request, response);
    }

    private void iniciarVenda(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int codInternacao = Integer.parseInt(request.getParameter("codInternacao"));
        Internacao internacao = internacaoDAO.buscarInternacaoPorId(codInternacao);

        HttpSession session = request.getSession();
        session.setAttribute("internacaoAtiva", internacao);
        session.setAttribute("carrinho", new ArrayList<MedicacaoInternacao>());

        listar(request, response);
    }

    private void adicionarAoCarrinho(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int medicacaoCod = Integer.parseInt(request.getParameter("medicacaoCod"));
        int quantidade = Integer.parseInt(request.getParameter("quantidade"));

        Medicacao medicacao = medicacaoDAO.buscarMedicacaoPorId(medicacaoCod);
        
        MedicacaoInternacao item = new MedicacaoInternacao();
        item.setMedicacao(medicacao);
        item.setQuantidadeRemedio(quantidade);

        HttpSession session = request.getSession();
        List<MedicacaoInternacao> carrinho = (List<MedicacaoInternacao>) session.getAttribute("carrinho");
        carrinho.add(item);

        listar(request, response);
    }
    
    private void removerDoCarrinho(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int index = Integer.parseInt(request.getParameter("index"));
        HttpSession session = request.getSession();
        List<MedicacaoInternacao> carrinho = (List<MedicacaoInternacao>) session.getAttribute("carrinho");
        if (carrinho != null && index >= 0 && index < carrinho.size()) {
            carrinho.remove(index);
        }
        listar(request, response);
    }

    private void finalizarVenda(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        List<MedicacaoInternacao> carrinho = (List<MedicacaoInternacao>) session.getAttribute("carrinho");
        Internacao internacao = (Internacao) session.getAttribute("internacaoAtiva");

        if (carrinho != null && !carrinho.isEmpty()) {
            for (MedicacaoInternacao item : carrinho) {
                item.setInternacao(internacao);
                dao.salvar(item);
            }
        }
        
        session.removeAttribute("carrinho");
        session.removeAttribute("internacaoAtiva");

        request.setAttribute("mensagem", "Dispensação de medicamentos registrada com sucesso!");
        listar(request, response);
    }
    
    private void cancelarVenda(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.removeAttribute("carrinho");
        session.removeAttribute("internacaoAtiva");
        listar(request, response);
    }


    private void encaminharParaPagina(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/CadastroMedicacaoInternacao.jsp");
        dispatcher.forward(request, response);
    }
}