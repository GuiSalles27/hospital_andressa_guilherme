/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hospital.dao.entidade;


/**
 *
 * @author 12067151622
 */
public class Internacao {
    private Integer codInternacao, sala;
    private String dataHora, descricao;
    private Paciente pacienteInternacao = new Paciente();
    private Funcionario funcionarioInternacao = new Funcionario();

    public Integer getCodInternacao() {
        return codInternacao;
    }

    public void setCodInternacao(Integer codInternacao) {
        this.codInternacao = codInternacao;
    }

    public Integer getSala() {
        return sala;
    }

    public void setSala(Integer sala) {
        this.sala = sala;
    }

    public String getDataHora() {
        return dataHora;
    }

    public void setDataHora(String dataHora) {
        this.dataHora = dataHora;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Paciente getPacienteInternacao() {
        return pacienteInternacao;
    }

    public void setPacienteInternacao(Paciente pacienteInternacao) {
        this.pacienteInternacao = pacienteInternacao;
    }

    public Funcionario getFuncionarioInternacao() {
        return funcionarioInternacao;
    }

    public void setFuncionarioInternacao(Funcionario funcionarioInternacao) {
        this.funcionarioInternacao = funcionarioInternacao;
    }
    
    
}
