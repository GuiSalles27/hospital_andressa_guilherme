/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hospital.dao;

import com.mycompany.hospital.dao.entidade.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author Tulio Dias
 */
public class UsuarioDAO extends GenericoDAO<Usuario>{
    
  /**
 * Atualiza a senha (já em formato bcrypt) do usuário identificado por id.
 */
    public void atualizarSenha(int userId, String senhaBcrypt) {
    String sql = "UPDATE usuario SET senhahash = ? WHERE id = ?";
    try (Connection con = ConnectionFactory.getInstance().getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {
        ps.setString(1, senhaBcrypt);
        ps.setInt(2, userId);
        ps.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

    
     public Usuario buscarUsuarioPorNomeUsuario(String nomeUsuario) {
        String sql = "SELECT nomeusuario, senhahash FROM usuario WHERE nomeusuario = ?";
        try (Connection con = ConnectionFactory.getInstance().getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, nomeUsuario);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Usuario(rs.getString("nomeusuario"), rs.getString("senhahash"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
     
     public void registrarUsuario(String nomeUsuario, String senhaSimples, String email) {
    // Gerar hash da senha
    String hashDeSenha = BCrypt.hashpw(senhaSimples, BCrypt.gensalt());

    // Inserir usuário no banco de dados
    String sql = "INSERT INTO usuario (nomeusuario, senhahash,email) VALUES (?, ?,?)";
    try (Connection con = ConnectionFactory.getInstance().getConnection();
         PreparedStatement stmt = con.prepareStatement(sql)) {

        stmt.setString(1, nomeUsuario);
        stmt.setString(2, hashDeSenha);
        stmt.setString(3, email);
        stmt.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    }
    }
     
     
     /* adicionar dependencia no pom.xml
     
     <dependency>
            <groupId>org.mindrot</groupId>
            <artifactId>jbcrypt</artifactId>
            <version>0.4</version>
        </dependency>
     
     */
     
    
}
