/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hospital.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * Padrão Singlenton
 */
public class ConnectionFactory {
    /*private static final String DB_URL = "jdbc:mysql://localhost:3306/guilhermes_andressa_hospital?useSSL=false&serverTimezone=America/Sao_Paulo"; //A versão 8 do Heid possui criptografia 
    private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD= "";*/
    
        //github / railway
    private static final String DB_URL = System.getenv("DB_URL");
    private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_USER = System.getenv("DB_USER");
    private static final String DB_PASSWORD = System.getenv("DB_PASSWORD");
    
    //variável estática que mantém a instância única de ConnectionFactory. Enqunato não se encerra uma intância não abre outra.
    private static ConnectionFactory instance;
    
    // o construtor á privado para impedir a criação direta de instâncias da classe fora dela mesma
    private ConnectionFactory(){
        try{
            Class.forName(DB_DRIVER);
        }catch(ClassNotFoundException e){
            throw new RuntimeException("Driver do banco de dados não encontrado",e);
        }
    }
  /* Método static ConnectionFactory: método público estático que permite o acesso a instância única
    da ConnectionFactory -> Padrão Singlenton: garante que apenas uma instância seja usada em toda
    a aplicação*/  
    public static ConnectionFactory getInstance(){
        if(instance==null){
            instance=new ConnectionFactory();
        }
        return instance;
    }
    
    public Connection getConnection() throws SQLException{
        return DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
    }
    
    public PreparedStatement getPreparedStatement(String sql) throws SQLException{
       Connection con = getConnection();
       //PreparedSTatement.RETURN_GENERATED_KEYS -> recupera o id gerado pelo
       //banco após a inserção de registro
       return con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
    }
}
