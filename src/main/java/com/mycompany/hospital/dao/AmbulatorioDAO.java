/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hospital.dao;

import com.mycompany.hospital.dao.entidade.Ambulatorio;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author 15114560603
 */
public class AmbulatorioDAO extends GenericoDAO<Ambulatorio>{
    
    public void salvar(Ambulatorio objAmbulatorio){
        String sql = "INSERT INTO AMBULATORIO(horarioAtendimento,quantAmbulancias,hospitalAmbulatorio) VALUES(?,?,?)";
        save(sql, objAmbulatorio.getHorarioAtendimento(), objAmbulatorio.getQuantAmbulancias(),objAmbulatorio.getHospitalAmbulatorio().getCodHospital());
    }
    
    public void alterar(Ambulatorio objAmbulatorio){
        String sql = "UPDATE AMBULATORIO SET horarioAtendimento=?, quantAmbulancias=?, hospitalAmbulatorio=? WHERE codAmbulatorio=?";
        save(sql, objAmbulatorio.getHorarioAtendimento(),objAmbulatorio.getQuantAmbulancias(), objAmbulatorio.getHospitalAmbulatorio().getCodHospital(), objAmbulatorio.getCodAmbulatorio());
     }
     
    public void excluir(Ambulatorio objAmbulatorio){
        String sql="DELETE FROM AMBULATORIO WHERE codAmbulatorio=?";
        save(sql, objAmbulatorio.getCodAmbulatorio());
    } 
    
    private static class AmbulatorioRowMapper implements RowMapper<Ambulatorio>{
       
        HospitalDAO objHospitalDao = new HospitalDAO();
        
        @Override
            public Ambulatorio mapRow(ResultSet rs) throws SQLException {
            Ambulatorio objAmbulatorio = new Ambulatorio();
            objAmbulatorio.setCodAmbulatorio(rs.getInt("codAmbulatorio"));
            objAmbulatorio.setHorarioAtendimento(rs.getString("horarioAtendimento"));
            objAmbulatorio.setQuantAmbulancias(rs.getInt("quantAmbulancias"));
            
            
            objAmbulatorio.setHospitalAmbulatorio(objHospitalDao.buscarHospitalPorId(rs.getInt("hospitalAmbulatorio")));
            
            return objAmbulatorio;
        }
        
    }
    
    public List<Ambulatorio> buscarTodosAmbulatorio(){
        String sql = "SELECT * FROM AMBULATORIO";
        return buscarTodos(sql, new AmbulatorioRowMapper());
    }
    
    public Ambulatorio buscarAmbulatorioPorId(int id){
        String sql ="SELECT * FROM AMBULATORIO WHERE codAmbulatorio=?";
        return buscarPorId(sql, new AmbulatorioRowMapper(),id);
    }
    
    public List<Ambulatorio> buscarPorHospital(int codHospital) {
    String sql = "SELECT * FROM AMBULATORIO WHERE hospitalAmbulatorio=?";
    return buscarTodos(sql, new AmbulatorioRowMapper(), codHospital);
}
    
    
}
