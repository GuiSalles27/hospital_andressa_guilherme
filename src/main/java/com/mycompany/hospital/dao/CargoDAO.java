package com.mycompany.hospital.dao;

import com.mycompany.hospital.dao.entidade.Cargo;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CargoDAO extends GenericoDAO<Cargo>{
    
    public void salvar(Cargo objCargo){
        String sql = "INSERT INTO cargo(nomeCargo,salarioInicial,descricao,bonificacao) VALUES(?,?,?,?)";
        save(sql, objCargo.getNomeCargo(), objCargo.getSalarioInicial(), objCargo.getDescricao(), objCargo.getBonificacao());
    }
    
    public void alterar(Cargo objCargo){
        String sql = "UPDATE cargo SET nomeCargo=?,salarioInicial=?,descricao=?,bonificacao=?  WHERE codCargo=?";
        save(sql,  objCargo.getNomeCargo(), objCargo.getSalarioInicial(), objCargo.getDescricao(), objCargo.getBonificacao(), objCargo.getCodCargo());
     }
     
    public void excluir(Cargo objCargo){
        String sql="DELETE FROM cargo WHERE codCargo=?";
        save(sql, objCargo.getCodCargo());
    } 
    
    private static class CargoRowMapper implements RowMapper<Cargo>{
        @Override
        public Cargo mapRow(ResultSet rs) throws SQLException {
            Cargo objCargo = new Cargo();
            objCargo.setCodCargo(rs.getInt("codCargo"));
            objCargo.setNomeCargo(rs.getString("nomeCargo"));
            objCargo.setSalarioInicial(rs.getDouble("salarioInicial"));
            objCargo.setDescricao(rs.getString("descricao"));
            objCargo.setBonificacao(rs.getDouble("bonificacao"));
            return objCargo;
        }
    }
    
    public List<Cargo> buscarTodosCargos(){
        String sql = "SELECT * FROM cargo";
        return buscarTodos(sql, new CargoRowMapper());
    }
    
    public Cargo buscarCargoPorId(int id){
        String sql ="SELECT * FROM cargo WHERE codCargo=?";
        return buscarPorId(sql, new CargoRowMapper(),id);
    }

    // 🔹 Novo método para buscar cargos por faixa salarial
    public List<Cargo> buscarPorFaixaSalarial(Double salarioMin, Double salarioMax) {
        String sql = "SELECT * FROM cargo WHERE salarioInicial BETWEEN ? AND ?";
        return buscarPorParametros(sql, new CargoRowMapper(), salarioMin, salarioMax);
    }
}
