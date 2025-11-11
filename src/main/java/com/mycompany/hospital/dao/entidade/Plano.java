/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hospital.dao.entidade;

/**
 *
 * @author 12067151622
 */
public class Plano {
    private Integer codPlano;
    private String nomePlano, tipoPlano;
    private Double valorPlano;

    public Integer getCodPlano() {
        return codPlano;
    }

    public void setCodPlano(Integer codPlano) {
        this.codPlano = codPlano;
    }

    public String getNomePlano() {
        return nomePlano;
    }

    public void setNomePlano(String nomePlano) {
        this.nomePlano = nomePlano;
    }

    public String getTipoPlano() {
        return tipoPlano;
    }

    public void setTipoPlano(String tipoPlano) {
        this.tipoPlano = tipoPlano;
    }

    public Double getValorPlano() {
        return valorPlano;
    }

    public void setValorPlano(Double valorPlano) {
        this.valorPlano = valorPlano;
    }

    

    
}
