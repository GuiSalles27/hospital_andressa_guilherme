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
public class Funcionario {
    private Integer codFunc;
    private String nomeFunc, cpf, rg, numRegistro, horarioTrabalho;
    private Calendar dataAdmissao;
    private Cargo cargoFuncionario = new Cargo();
    private Hospital hospitalFuncionario = new Hospital();

    public Integer getCodFunc() {
        return codFunc;
    }

    public void setCodFunc(Integer codFunc) {
        this.codFunc = codFunc;
    }

    public String getNomeFunc() {
        return nomeFunc;
    }

    public void setNomeFunc(String nomeFunc) {
        this.nomeFunc = nomeFunc;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getNumRegistro() {
        return numRegistro;
    }

    public void setNumRegistro(String numRegistro) {
        this.numRegistro = numRegistro;
    }

    public String getHorarioTrabalho() {
        return horarioTrabalho;
    }

    public void setHorarioTrabalho(String horarioTrabalho) {
        this.horarioTrabalho = horarioTrabalho;
    }

    public Calendar getDataAdmissao() {
        return dataAdmissao;
    }

    public void setDataAdmissao(Calendar dataAdmissao) {
        this.dataAdmissao = dataAdmissao;
    }

    public Cargo getCargoFuncionario() {
        return cargoFuncionario;
    }

    public void setCargoFuncionario(Cargo cargoFuncionario) {
        this.cargoFuncionario = cargoFuncionario;
    }

    public Hospital getHospitalFuncionario() {
        return hospitalFuncionario;
    }

    public void setHospitalFuncionario(Hospital hospitalFuncionario) {
        this.hospitalFuncionario = hospitalFuncionario;
    }

   

    
    
    public String getDataAdmissaoFormatado() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(dataAdmissao.getTime());
    }
    
}
