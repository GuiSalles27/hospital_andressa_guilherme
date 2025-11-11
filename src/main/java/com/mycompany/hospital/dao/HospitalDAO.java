/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hospital.dao;

import com.mycompany.hospital.dao.entidade.Hospital;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author 15114560603
 */
public class HospitalDAO extends GenericoDAO<Hospital>{
    
    public void salvar(Hospital objHospital){
        String sql = "INSERT INTO HOSPITAL(cnpjHospital,endereco,bairro,cidade,cep,uf,telefone,descricao) VALUES(?,?,?,?,?,?,?,?)";
        save(sql, objHospital.getCnpjHospital(), objHospital.getEndereco(), objHospital.getBairro(), objHospital.getCidade(), objHospital.getCep(), objHospital.getUf(), objHospital.getTelefone(), objHospital.getDescricao());
    }
    
    public void alterar(Hospital objHospital){
        String sql = "UPDATE HOSPITAL SET cnpjHospital=?,endereco=?,bairro=?,cidade=?,cep=?,uf=?,telefone=?,descricao=?  WHERE codHospital=?";
        save(sql, objHospital.getCnpjHospital(), objHospital.getEndereco(), objHospital.getBairro(), objHospital.getCidade(), objHospital.getCep(), objHospital.getUf(), objHospital.getTelefone(), objHospital.getDescricao(), objHospital.getCodHospital());
     }
     
    public void excluir(Hospital objHospital){
        String sql="DELETE FROM HOSPITAL WHERE codHospital=?";
        save(sql, objHospital.getCodHospital());
    } 
    
    private static class CargoRowMapper implements RowMapper<Hospital>{

        @Override
        public Hospital mapRow(ResultSet rs) throws SQLException {
            Hospital objHospital = new Hospital();
            objHospital.setCodHospital(rs.getInt("codHospital"));
            objHospital.setCnpjHospital(rs.getString("cnpjHospital"));
            objHospital.setEndereco(rs.getString("endereco"));
            objHospital.setEndereco(rs.getString("endereco"));
            objHospital.setBairro(rs.getString("bairro"));
            objHospital.setCidade(rs.getString("cidade"));
            objHospital.setCep(rs.getString("cep"));
            objHospital.setUf(rs.getString("uf"));
            objHospital.setTelefone(rs.getString("telefone"));
            objHospital.setDescricao(rs.getString("descricao"));
            
            
            return objHospital;
        }
        
    }
    
    public List<Hospital> buscarTodosHospitais(){
        String sql = "SELECT * FROM HOSPITAL";
        return buscarTodos(sql, new CargoRowMapper());
    }
    
    public Hospital buscarHospitalPorId(int id){
        String sql ="SELECT * FROM HOSPITAL WHERE codHospital=?";
        return buscarPorId(sql, new CargoRowMapper(),id);
    }
}
