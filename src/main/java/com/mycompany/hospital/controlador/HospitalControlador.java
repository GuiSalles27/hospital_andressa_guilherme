/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hospital.controlador;

import com.mycompany.hospital.dao.HospitalDAO;
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

/**
 *
 * @author 15114560603
 */
//@webServlet: designar uma classe com um Serviets, mapeamento da url no servidor WEB-> GlassFish, indica a cidade e faz um mapeamento. Se der erro 404 o erro é aqui
@WebServlet(WebConstante.BASE_PATH+"/HospitalControlador")
public class HospitalControlador extends HttpServlet{//herda de httpServlet para reescrever os métodos
    private Hospital objHospital;
    private HospitalDAO objhospitalDao;
    String cnpjHospital,endereco,bairro,cidade,cep,uf,telefone,descricao,codHospital;

    @Override
    public void init() throws ServletException {
        objhospitalDao = new HospitalDAO();
        objHospital = new Hospital();
        
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            String opcao = request.getParameter("opcao");
            if(opcao==null||opcao.isEmpty()){
                opcao="listar";
            }
            codHospital = request.getParameter("codHospital");
            cnpjHospital = request.getParameter("cnpjHospital");
            endereco = request.getParameter("endereco");
            bairro = request.getParameter("bairro");
            cidade = request.getParameter("cidade");
            cep = request.getParameter("cep");
            uf = request.getParameter("uf");
            telefone = request.getParameter("telefone");
            descricao = request.getParameter("descricao");
            
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
                case "excluir":
                    excluir(request,response);
                case "confirmarExcluir":
                    confirmarExcluir(request,response);
                case "cancelar":
                    cancelar(request,response);
                default:
                    throw new IllegalArgumentException("Opção inválida "+opcao);
            }      
    }catch(NumberFormatException e){ // lida com erros de conversão de tipo numérico
        response.getWriter().println("Erro: um ou mais parâmetros não são numéricos válidos"+e.getMessage());
    }catch(IllegalArgumentException e){//lida com erros de parâmetros ausente
        response.getWriter().println("Erro: Parâmetros ausentes "+e.getMessage());
    }
    }
    
   protected void cadastrar(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException {

    // Recupera os dados do formulário
    String codHospital = request.getParameter("codHospital");
    String cnpjHospital = request.getParameter("cnpjHospital");
    String endereco = request.getParameter("endereco");
    String bairro = request.getParameter("bairro");
    String cidade = request.getParameter("cidade");
    String cep = request.getParameter("cep");
    String uf = request.getParameter("uf");
    String telefone = request.getParameter("telefone");
    String descricao = request.getParameter("descricao");

    // Validação simples dos campos obrigatórios
    if (cnpjHospital == null || cnpjHospital.trim().isEmpty() ||
        endereco == null || endereco.trim().isEmpty() ||
        bairro == null || bairro.trim().isEmpty() ||
        cidade == null || cidade.trim().isEmpty() ||
        cep == null || cep.trim().isEmpty() ||
        uf == null || uf.trim().isEmpty() ||
        telefone == null || telefone.trim().isEmpty() ||
        descricao == null || descricao.trim().isEmpty()) {

        request.setAttribute("mensagem", "Erro: Os campos não podem ser nulos. Por gentileza, preencha-os.");
        request.setAttribute("codHospital", codHospital);
        request.setAttribute("cnpjHospital", cnpjHospital);
        request.setAttribute("endereco", endereco);
        request.setAttribute("bairro", bairro);
        request.setAttribute("cidade", cidade);
        request.setAttribute("cep", cep);
        request.setAttribute("uf", uf);
        request.setAttribute("telefone", telefone);
        request.setAttribute("descricao", descricao);
        request.setAttribute("opcao", "cadastrar");
        encaminharParaPagina(request, response);
        return;
    }

    // Bloco de persistência
    try {
        objHospital.setCnpjHospital(cnpjHospital);
        objHospital.setEndereco(endereco);
        objHospital.setBairro(bairro);
        objHospital.setCidade(cidade);
        objHospital.setCep(cep);
        objHospital.setUf(uf);
        objHospital.setTelefone(telefone);
        objHospital.setDescricao(descricao);

        objhospitalDao.salvar(objHospital);
        encaminharParaPagina(request, response);

    } catch (Exception e) {
        request.setAttribute("mensagem", "Erro ao cadastrar hospital: " + e.getMessage());
        encaminharParaPagina(request, response);
    }
}


    protected void editar(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException{ 
        request.setAttribute("codHospital", codHospital);
        request.setAttribute("cnpjHospital", cnpjHospital);
        request.setAttribute("endereco", endereco);
        request.setAttribute("bairro", bairro);
        request.setAttribute("cidade", cidade);
        request.setAttribute("cep", cep);
        request.setAttribute("uf", uf);
        request.setAttribute("telefone", telefone);
        request.setAttribute("descricao", descricao);
        request.setAttribute("mensagem", "Edite os dados e clique em salvar");
        request.setAttribute("opcao", "confirmarEditar");
        encaminharParaPagina(request, response);
    } 
    protected void confirmarEditar(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException {

    // Recupera os parâmetros do formulário
    String codHospitalStr = request.getParameter("codHospital");
    String cnpjHospital = request.getParameter("cnpjHospital");
    String endereco = request.getParameter("endereco");
    String bairro = request.getParameter("bairro");
    String cidade = request.getParameter("cidade");
    String cep = request.getParameter("cep");
    String uf = request.getParameter("uf");
    String telefone = request.getParameter("telefone");
    String descricao = request.getParameter("descricao");

    // Verificação de campos obrigatórios
    if (codHospitalStr == null || codHospitalStr.trim().isEmpty() ||
        cnpjHospital == null || cnpjHospital.trim().isEmpty() ||
        endereco == null || endereco.trim().isEmpty() ||
        bairro == null || bairro.trim().isEmpty() ||
        cidade == null || cidade.trim().isEmpty() ||
        cep == null || cep.trim().isEmpty() ||
        uf == null || uf.trim().isEmpty() ||
        telefone == null || telefone.trim().isEmpty() ||
        descricao == null || descricao.trim().isEmpty()) {

        request.setAttribute("mensagem", "Erro: preencha todos os campos obrigatórios.");
        request.setAttribute("opcao", "editar");

        // Retorna os dados preenchidos ao formulário
        request.setAttribute("codHospital", codHospitalStr);
        request.setAttribute("cnpjHospital", cnpjHospital);
        request.setAttribute("endereco", endereco);
        request.setAttribute("bairro", bairro);
        request.setAttribute("cidade", cidade);
        request.setAttribute("cep", cep);
        request.setAttribute("uf", uf);
        request.setAttribute("telefone", telefone);
        request.setAttribute("descricao", descricao);

        encaminharParaPagina(request, response);
        return;
    }

    try {
        objHospital.setCodHospital(Integer.parseInt(codHospitalStr));
        objHospital.setCnpjHospital(cnpjHospital);
        objHospital.setEndereco(endereco);
        objHospital.setBairro(bairro);
        objHospital.setCidade(cidade);
        objHospital.setCep(cep);
        objHospital.setUf(uf);
        objHospital.setTelefone(telefone);
        objHospital.setDescricao(descricao);

        objhospitalDao.alterar(objHospital);
        encaminharParaPagina(request, response);

    } catch (NumberFormatException e) {
        request.setAttribute("mensagem", "Erro: preencha corretamente os campos numéricos (Código do hospital).");
        request.setAttribute("opcao", "editar");

        // Repassa os dados preenchidos ao formulário
        request.setAttribute("codHospital", codHospitalStr);
        request.setAttribute("cnpjHospital", cnpjHospital);
        request.setAttribute("endereco", endereco);
        request.setAttribute("bairro", bairro);
        request.setAttribute("cidade", cidade);
        request.setAttribute("cep", cep);
        request.setAttribute("uf", uf);
        request.setAttribute("telefone", telefone);
        request.setAttribute("descricao", descricao);

        encaminharParaPagina(request, response);
    }
}

    protected void excluir (HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException{ 
        request.setAttribute("codHospital", codHospital);
        request.setAttribute("cnpjHospital", cnpjHospital);
        request.setAttribute("endereco", endereco);
        request.setAttribute("bairro", bairro);
        request.setAttribute("cidade", cidade);
        request.setAttribute("cep", cep);
        request.setAttribute("uf", uf);
        request.setAttribute("telefone", telefone);
        request.setAttribute("descricao", descricao);
        request.setAttribute("mensagem", "Clique em salvar para excluir");
        request.setAttribute("opcao", "confirmarExcluir");
        encaminharParaPagina(request, response);
    } 
    protected void confirmarExcluir(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException{ 
        objHospital.setCodHospital(Integer.valueOf(codHospital));
        objhospitalDao.excluir(objHospital);
        encaminharParaPagina(request, response);
        
    }
    private void encaminharParaPagina(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException{
        List<Hospital> listaHospital = objhospitalDao.buscarTodosHospitais();
        request.setAttribute("listaHospital", listaHospital);
        RequestDispatcher enviar = request.getRequestDispatcher("/CadastroHospital.jsp");
        enviar.forward(request, response);
    }
    protected void cancelar(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException{
        request.setAttribute("codHospital", "0");
        request.setAttribute("cnpjHospital", "");
        request.setAttribute("endereco", "");
        request.setAttribute("bairro", "");
        request.setAttribute("cidade", "");
        request.setAttribute("cep", "");
        request.setAttribute("uf", "");
        request.setAttribute("telefone", "");
        request.setAttribute("descricao", "");
        request.setAttribute("opcao", "cadastrar");
        encaminharParaPagina(request, response);
    }
    }


