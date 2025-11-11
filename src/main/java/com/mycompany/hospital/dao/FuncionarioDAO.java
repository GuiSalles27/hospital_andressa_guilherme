/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hospital.dao;

import com.mycompany.hospital.dao.entidade.Cargo;
import com.mycompany.hospital.dao.entidade.Funcionario;
import com.mycompany.hospital.dao.entidade.Hospital;
import com.mycompany.hospital.servico.ConverteData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author 12067151622
 */
public class FuncionarioDAO extends GenericoDAO<Funcionario>{
    
    public void salvar(Funcionario objFuncionario){
        String sql = "INSERT INTO FUNCIONARIO(NOMEFUNC,CPF,RG,NUMREGISTRO,DATAADMISSAO,HORARIOTRABALHO,cargoFuncionario,hospitalFuncionario) VALUES(?,?,?,?,?,?,?,?)";
        save(sql, objFuncionario.getNomeFunc(), objFuncionario.getCpf(), objFuncionario.getRg(), objFuncionario.getNumRegistro(), objFuncionario.getDataAdmissao(), objFuncionario.getHorarioTrabalho(), objFuncionario.getCargoFuncionario().getCodCargo(), objFuncionario.getHospitalFuncionario().getCodHospital());
    }
    
    public void alterar(Funcionario objFuncionario){
        String sql = "UPDATE FUNCIONARIO SET NOMEFUNC=?, CPF=?, RG=?, NUMREGISTRO=?, DATAADMISSAO=?, HORARIOTRABALHO=?, cargoFuncionario=?, hospitalFuncionario=? WHERE CODFUNC=?";
        save(sql, objFuncionario.getNomeFunc(), objFuncionario.getCpf(), objFuncionario.getRg(), objFuncionario.getNumRegistro(), objFuncionario.getDataAdmissao(), objFuncionario.getHorarioTrabalho(), objFuncionario.getCargoFuncionario().getCodCargo(), objFuncionario.getHospitalFuncionario().getCodHospital(), objFuncionario.getCodFunc());
     }
     
    public void excluir(Funcionario objFuncionario){
        String sql="DELETE FROM FUNCIONARIO WHERE CODFUNC=?";
        save(sql, objFuncionario.getCodFunc());
    } 
    
    private static class FuncionarioRowMapper implements RowMapper<Funcionario> {
    ConverteData converte = new ConverteData();

    @Override
    public Funcionario mapRow(ResultSet rs) throws SQLException {
        Funcionario objFuncionario = new Funcionario();
        objFuncionario.setCodFunc(rs.getInt("codFunc"));
        objFuncionario.setNomeFunc(rs.getString("nomeFunc"));
        objFuncionario.setCpf(rs.getString("cpf"));
        objFuncionario.setRg(rs.getString("rg"));
        objFuncionario.setNumRegistro(rs.getString("numRegistro"));
        objFuncionario.setDataAdmissao(converte.converteCalendario(rs.getDate("dataAdmissao")));
        objFuncionario.setHorarioTrabalho(rs.getString("horarioTrabalho"));

        // Cargo vindo do JOIN
        Cargo cargo = new Cargo();
        cargo.setCodCargo(rs.getInt("codCargo"));
        cargo.setNomeCargo(rs.getString("nomeCargo"));
        cargo.setSalarioInicial(rs.getDouble("salarioInicial"));
        objFuncionario.setCargoFuncionario(cargo);

        // Hospital vindo do JOIN
        Hospital hospital = new Hospital();
        hospital.setCodHospital(rs.getInt("codHospital"));
        hospital.setCidade(rs.getString("cidade"));
        hospital.setBairro(rs.getString("bairro"));
        objFuncionario.setHospitalFuncionario(hospital);

        return objFuncionario;
    }
}

    
    public List<Funcionario> buscarTodosFuncionario(){
    String sql = "SELECT f.codFunc, f.nomeFunc, f.cpf, f.rg, f.numRegistro, " +
                 "f.dataAdmissao, f.horarioTrabalho, " +
                 "c.codCargo, c.nomeCargo, c.salarioInicial, " +
                 "h.codHospital, h.cidade, h.bairro " +
                 "FROM funcionario f " +
                 "LEFT JOIN cargo c ON f.cargoFuncionario = c.codCargo " +
                 "LEFT JOIN hospital h ON f.hospitalFuncionario = h.codHospital";
    return buscarTodos(sql, new FuncionarioRowMapper());
}

public Funcionario buscarFuncionarioPorId(int id){
    String sql ="SELECT f.codFunc, f.nomeFunc, f.cpf, f.rg, f.numRegistro, " +
                "f.dataAdmissao, f.horarioTrabalho, " +
                "c.codCargo, c.nomeCargo, c.salarioInicial, " +
                "h.codHospital, h.cidade, h.bairro " +
                "FROM funcionario f " +
                "LEFT JOIN cargo c ON f.cargoFuncionario = c.codCargo " +
                "LEFT JOIN hospital h ON f.hospitalFuncionario = h.codHospital " +
                "WHERE f.codFunc = ?";
    return buscarPorId(sql, new FuncionarioRowMapper(), id);
}

    
   public List<Funcionario> buscarTodosComCargoHospital() {
    String sql = "SELECT f.codFunc, f.nomeFunc, f.cpf, f.rg, f.numRegistro, " +
                 "f.dataAdmissao, f.horarioTrabalho, " +
                 "c.codCargo, c.nomeCargo, c.salarioInicial, " +
                 "h.codHospital, h.cidade, h.bairro " +
                 "FROM funcionario f " +
                 "LEFT JOIN cargo c ON f.cargoFuncionario = c.codCargo " +
                 "LEFT JOIN hospital h ON f.hospitalFuncionario = h.codHospital";
    return buscarTodos(sql, new FuncionarioRowMapper());
}


}
