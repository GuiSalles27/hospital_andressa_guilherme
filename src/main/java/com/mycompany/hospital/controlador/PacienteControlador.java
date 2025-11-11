/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hospital.controlador;

import com.mycompany.hospital.dao.PlanoDAO;
import com.mycompany.hospital.dao.PacienteDAO;
import com.mycompany.hospital.dao.entidade.Plano;
import com.mycompany.hospital.dao.entidade.Paciente;
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
@WebServlet(WebConstante.BASE_PATH+"/PacienteControlador")
public class PacienteControlador extends HttpServlet{//herda de httpServlet para reescrever os métodos
   
    private Paciente objPaciente;
    private PacienteDAO objPacienteDao;
    private final ConverteData converte = new ConverteData();
    
    private Plano objPlano;
    private PlanoDAO objplanoDao;
    String codPaciente, nomePaciente, cpf, dataNascimento, filiacao, rg, endereco, bairro, cidade, cep, uf, telefone, planoPaciente;

    @Override
    public void init() throws ServletException {
        objplanoDao = new PlanoDAO();
        objPlano = new Plano();
        objPaciente = new Paciente();
        objPacienteDao = new PacienteDAO();
        
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            String opcao = request.getParameter("opcao");
            if(opcao==null||opcao.isEmpty()){
                opcao="listar";
            }
            codPaciente = request.getParameter("codPaciente");
            nomePaciente = request.getParameter("nomePaciente");
            cpf = request.getParameter("cpf");
            dataNascimento = request.getParameter("dataNascimento");
            filiacao = request.getParameter("filiacao");
            rg = request.getParameter("rg");
            endereco = request.getParameter("endereco");
            bairro = request.getParameter("bairro");
            cidade = request.getParameter("cidade");
            cep = request.getParameter("cep");            
            uf = request.getParameter("uf");            
            telefone = request.getParameter("telefone");            
            planoPaciente = request.getParameter("planoPaciente");
            
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
        throws ServletException, IOException{ 
    objPaciente.setNomePaciente(nomePaciente);
    objPaciente.setCpf(cpf);
    objPaciente.setDataNascimento(converte.converteCalendario(dataNascimento));
    objPaciente.setFiliacao(filiacao);
    objPaciente.setRg(rg);
    objPaciente.setEndereco(endereco);
    objPaciente.setBairro(bairro);
    objPaciente.setCidade(cidade);
    objPaciente.setCep(cep);
    objPaciente.setUf(uf);
    objPaciente.setTelefone(telefone);

    Plano plano = new Plano();
    plano.setCodPlano(Integer.valueOf(planoPaciente));
    objPaciente.setPlanoPaciente(plano);

    objPacienteDao.salvar(objPaciente);
    encaminharParaPagina(request, response);
}

    
    protected void editar(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException{ 
        request.setAttribute("codPaciente", codPaciente);
        request.setAttribute("nomePaciente", nomePaciente);
        request.setAttribute("cpf", cpf);
        request.setAttribute("dataNascimento", ConverteData.convertDateFormat(dataNascimento));
        request.setAttribute("filiacao", filiacao);
        request.setAttribute("rg", rg);
        request.setAttribute("endereco", endereco);
        request.setAttribute("bairro", bairro);
        request.setAttribute("cidade", cidade);
        request.setAttribute("cep", cep);
        request.setAttribute("uf", uf);
        request.setAttribute("telefone", telefone);
        request.setAttribute("planoPaciente", planoPaciente);
        request.setAttribute("mensagem", "Edite os dados e clique em salvar");
        request.setAttribute("opcao", "confirmarEditar");
        encaminharParaPagina(request, response);
    } 
    protected void confirmarEditar(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException{ 
    objPaciente.setCodPaciente(Integer.valueOf(codPaciente));
    objPaciente.setNomePaciente(nomePaciente);
    objPaciente.setCpf(cpf);
    objPaciente.setDataNascimento(converte.converteCalendario(dataNascimento));
    objPaciente.setFiliacao(filiacao);
    objPaciente.setRg(rg);
    objPaciente.setEndereco(endereco);
    objPaciente.setBairro(bairro);
    objPaciente.setCidade(cidade);
    objPaciente.setCep(cep);
    objPaciente.setUf(uf);
    objPaciente.setTelefone(telefone);

    Plano plano = new Plano();
    plano.setCodPlano(Integer.valueOf(planoPaciente));
    objPaciente.setPlanoPaciente(plano);

    objPacienteDao.alterar(objPaciente);
    encaminharParaPagina(request, response);
}

    protected void excluir (HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException{ 
        request.setAttribute("codPaciente", codPaciente);
        request.setAttribute("nomePaciente", nomePaciente);
        request.setAttribute("cpf", cpf);
        request.setAttribute("dataNascimento", ConverteData.convertDateFormat(dataNascimento));
        request.setAttribute("filiacao", filiacao);
        request.setAttribute("rg", rg);
        request.setAttribute("endereco", endereco);
        request.setAttribute("bairro", bairro);
        request.setAttribute("cidade", cidade);
        request.setAttribute("cep", cep);
        request.setAttribute("uf", uf);
        request.setAttribute("telefone", telefone);
        request.setAttribute("planoPaciente", planoPaciente);
        request.setAttribute("mensagem", "Clique em salvar para excluir");
        request.setAttribute("opcao", "confirmarExcluir");
        encaminharParaPagina(request, response);
    } 
    protected void confirmarExcluir(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException{ 
        objPaciente.setCodPaciente(Integer.valueOf(codPaciente));
        objPacienteDao.excluir(objPaciente);
        encaminharParaPagina(request, response);
        
    }
    private void encaminharParaPagina(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException{
        List<Plano> listaPlano = objplanoDao.buscarTodosPlanos();
        request.setAttribute("listaPlano", listaPlano);
        
        List<Paciente> listaPaciente = objPacienteDao.buscarTodosPaciente();
        request.setAttribute("listaPaciente", listaPaciente);
        
        RequestDispatcher enviar = request.getRequestDispatcher("/CadastroPaciente.jsp");
        enviar.forward(request, response);
    }
    protected void cancelar(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException{
        request.setAttribute("codPaciente", "0");
        request.setAttribute("nomePaciente", "");
        request.setAttribute("cpf", "");
        request.setAttribute("dataNascimento", ConverteData.convertDateFormat(""));
        request.setAttribute("filiacao", "");
        request.setAttribute("rg", "");
        request.setAttribute("endereco", "");
        request.setAttribute("bairro", "");
        request.setAttribute("cidade", "");
        request.setAttribute("cep", "");
        request.setAttribute("uf", "");
        request.setAttribute("telefone", "");
        request.setAttribute("planoPaciente", "");
        request.setAttribute("opcao", "cadastrar");
        encaminharParaPagina(request, response);
    }
    }


