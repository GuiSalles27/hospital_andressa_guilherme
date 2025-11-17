/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hospital.dao;

import com.mycompany.hospital.dao.entidade.Medicacao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 12067151622
 */
public class MedicacaoDAO extends GenericoDAO<Medicacao>{
    public void salvar(Medicacao objMedicacao){
        String sql = "INSERT INTO medicacao(tipoRemedio, valorRemedio, nomeRemedio, fabricacao, desconto) VALUES(?,?,?,?,?)";
        save(sql, objMedicacao.getTipoRemedio(), objMedicacao.getValorRemedio(), objMedicacao.getNomeRemedio(), objMedicacao.getFabricacao(), objMedicacao.getDesconto());
    }
    
    public void alterar(Medicacao objMedicacao){
        String sql = "UPDATE medicacao SET tipoRemedio=?, valorRemedio=?, nomeRemedio=?, fabricacao=?, desconto=? WHERE codRemedio=?";
        save(sql, objMedicacao.getTipoRemedio(), objMedicacao.getValorRemedio(), objMedicacao.getNomeRemedio(), objMedicacao.getFabricacao(), objMedicacao.getDesconto(), objMedicacao.getCodRemedio());
    }
    
    public void excluir(Medicacao objMedicacao){
        String sql = "DELETE FROM medicacao WHERE codRemedio=?";
        save(sql, objMedicacao.getCodRemedio());
    }
    
    private static class PlanoRowMapper implements RowMapper<Medicacao>{

        @Override
        public Medicacao mapRow(ResultSet rs) throws SQLException {
            Medicacao objMedicacao = new Medicacao();
            objMedicacao.setCodRemedio(rs.getInt("codRemedio"));
            objMedicacao.setTipoRemedio(rs.getString("tipoRemedio"));
            objMedicacao.setValorRemedio(rs.getDouble("valorRemedio"));
            objMedicacao.setNomeRemedio(rs.getString("nomeRemedio"));
            objMedicacao.setFabricacao(rs.getString("fabricacao"));
            objMedicacao.setDesconto(rs.getDouble("desconto"));
            return objMedicacao;
        }
        
    }
    
    public List<Medicacao> buscarTodasMedicacoes(){
        String sql = "SELECT * FROM medicacao";
        return buscarTodos(sql, new PlanoRowMapper());
    }
    
    public Medicacao buscarMedicacaoPorId(int id){
        String sql ="SELECT * FROM medicacao WHERE codRemedio=?";
        return buscarPorId(sql, new PlanoRowMapper(),id);
    }
    
   public List<Medicacao> buscarPorTipo(String tipo) {
        List<Medicacao> lista = new ArrayList<>();
        String sql = "SELECT * FROM medicacao WHERE tipoRemedio = ?";

        try (Connection con = ConnectionFactory.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, tipo);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Medicacao m = new Medicacao();
                m.setCodRemedio(rs.getInt("codRemedio"));
                m.setTipoRemedio(rs.getString("tipoRemedio"));
                m.setValorRemedio(rs.getDouble("valorRemedio"));
                m.setNomeRemedio(rs.getString("nomeRemedio"));
                m.setFabricacao(rs.getString("fabricacao"));
                // Verifica se o valor de desconto não é null para evitar NullPointerException
                Double desconto = rs.getObject("desconto") != null ? rs.getDouble("desconto") : null;
                m.setDesconto(desconto);

                lista.add(m);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

}
