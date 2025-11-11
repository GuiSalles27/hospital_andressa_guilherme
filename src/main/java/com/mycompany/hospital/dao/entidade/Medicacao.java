/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hospital.dao.entidade;

/**
 *
 * @author User
 */
public class Medicacao {
    private Integer codRemedio;
    private String tipoRemedio, nomeRemedio, fabricacao;
    private Double valorRemedio, desconto;
    private String dosagem; 
            
    public Integer getCodRemedio() {
        return codRemedio;
    }

    public void setCodRemedio(Integer codRemedio) {
        this.codRemedio = codRemedio;
    }

    public String getTipoRemedio() {
        return tipoRemedio;
    }

    public void setTipoRemedio(String tipoRemedio) {
        this.tipoRemedio = tipoRemedio;
    }

    public String getNomeRemedio() {
        return nomeRemedio;
    }

    public void setNomeRemedio(String nomeRemedio) {
        this.nomeRemedio = nomeRemedio;
    }

    public String getFabricacao() {
        return fabricacao;
    }

    public void setFabricacao(String fabricacao) {
        this.fabricacao = fabricacao;
    }

    public Double getValorRemedio() {
        return valorRemedio;
    }

    public void setValorRemedio(Double valorRemedio) {
        this.valorRemedio = valorRemedio;
    }

    public Double getDesconto() {
        return desconto;
    }

    public void setDesconto(Double desconto) {
        this.desconto = desconto;
    }
    
    public String getDosagem() {
    return dosagem;
}
    public void setDosagem(String dosagem) {
    this.dosagem = dosagem;
}

   
            
    
    
}
