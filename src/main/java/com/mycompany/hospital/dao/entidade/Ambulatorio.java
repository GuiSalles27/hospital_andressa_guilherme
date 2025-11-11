/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hospital.dao.entidade;
/**
 *
 * @author 15114560603
 */
public class Ambulatorio {
    private Integer codAmbulatorio;
    private String horarioAtendimento;
    private Integer quantAmbulancias;
    private String cidade;
    private String bairro;
    
     private Hospital hospitalAmbulatorio = new Hospital();

    public Integer getCodAmbulatorio() {
        return codAmbulatorio;
    }

    public void setCodAmbulatorio(Integer codAmbulatorio) {
        this.codAmbulatorio = codAmbulatorio;
    }

    public String getHorarioAtendimento() {
        return horarioAtendimento;
    }

    public void setHorarioAtendimento(String horarioAtendimento) {
        this.horarioAtendimento = horarioAtendimento;
    }

    public Integer getQuantAmbulancias() {
        return quantAmbulancias;
    }

    public void setQuantAmbulancias(Integer quantAmbulancias) {
        this.quantAmbulancias = quantAmbulancias;
    }

    public Hospital getHospitalAmbulatorio() {
        return hospitalAmbulatorio;
    }

    public void setHospitalAmbulatorio(Hospital hospitalAmbulatorio) {
        this.hospitalAmbulatorio = hospitalAmbulatorio;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }
    
    
    
}
