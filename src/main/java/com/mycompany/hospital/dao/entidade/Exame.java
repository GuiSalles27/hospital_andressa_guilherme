/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hospital.dao.entidade;

/**
 *
 * @author User
 */
public class Exame {
    private Integer codExame;
    private String tipoExame;
    private Double valor;
    private String resultado;
    
    private Paciente pacienteExame = new Paciente();
    private Funcionario funcionarioExame = new Funcionario();

    public Integer getCodExame() {
        return codExame;
    }

    public void setCodExame(Integer codExame) {
        this.codExame = codExame;
    }

    public String getTipoExame() {
        return tipoExame;
    }

    public void setTipoExame(String tipoExame) {
        this.tipoExame = tipoExame;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public Paciente getPacienteExame() {
        return pacienteExame;
    }

    public void setPacienteExame(Paciente pacienteExame) {
        this.pacienteExame = pacienteExame;
    }

    public Funcionario getFuncionarioExame() {
        return funcionarioExame;
    }

    public void setFuncionarioExame(Funcionario funcionarioExame) {
        this.funcionarioExame = funcionarioExame;
    }
    
    


    
}
