/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hospital.dao.entidade;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 *
 * @author 12067151622
 */
public class Consulta {
    private Integer codConsulta;
    private String diagnostico;
    private Calendar dataConsulta;
    
    private Paciente pacienteConsulta = new Paciente();
    private Funcionario funcionarioConsulta = new Funcionario();

    public Integer getCodConsulta() {
        return codConsulta;
    }

    public void setCodConsulta(Integer codConsulta) {
        this.codConsulta = codConsulta;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public Calendar getDataConsulta() {
        return dataConsulta;
    }

    public void setDataConsulta(Calendar dataConsulta) {
        this.dataConsulta = dataConsulta;
    }

    public Paciente getPacienteConsulta() {
        return pacienteConsulta;
    }

    public void setPacienteConsulta(Paciente pacienteConsulta) {
        this.pacienteConsulta = pacienteConsulta;
    }

    public Funcionario getFuncionarioConsulta() {
        return funcionarioConsulta;
    }

    public void setFuncionarioConsulta(Funcionario funcionarioConsulta) {
        this.funcionarioConsulta = funcionarioConsulta;
    }
    
    public String getDataConsultaFormatado() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(dataConsulta.getTime());
    }
    
}
