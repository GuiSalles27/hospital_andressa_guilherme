/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hospital.dao.entidade;

/**
 *
 * @author Tulio Dias
 */
public class Usuario {
    
    private String usuario;
    private String senhaHash;  // Senha criptografada


    public Usuario(String usuario, String senhaHash) {
        this.usuario = usuario;
        this.senhaHash = senhaHash;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenhaHash() {
        return senhaHash;
    }

    public void setSenhaHash(String senhaHash) {
        this.senhaHash = senhaHash;
    }

   
    
    /*
    sql para criar a tabela usuarios no banco de dados:
    
      
    CREATE TABLE usuario (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nomeusuario VARCHAR(255) NOT NULL UNIQUE,
    senhahash VARCHAR(60) NOT NULL,
    email VARCHAR(255)  
);
    
    */
    
}
