package com.mycompany.hospital.dao;

import com.mycompany.hospital.dao.entidade.Plano;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlanoDAO extends GenericoDAO<Plano> {

    public void salvar(Plano objPlano) {
        String sql = "INSERT INTO PLANO(NOMEPLANO, VALORPLANO, TIPOPLANO) VALUES(?,?,?)";
        save(sql, objPlano.getNomePlano(), objPlano.getValorPlano(), objPlano.getTipoPlano());
    }

    public void alterar(Plano objPlano) {
        String sql = "UPDATE PLANO SET NOMEPLANO=?, VALORPLANO=?, TIPOPLANO=? WHERE CODPLANO=?";
        save(sql, objPlano.getNomePlano(), objPlano.getValorPlano(), objPlano.getTipoPlano(), objPlano.getCodPlano());
    }

    public void excluir(Plano objPlano) {
        String sql = "DELETE FROM PLANO WHERE CODPLANO=?";
        save(sql, objPlano.getCodPlano());
    }

    private static class PlanoRowMapper implements RowMapper<Plano> {
        @Override
        public Plano mapRow(ResultSet rs) throws SQLException {
            Plano objPlano = new Plano();
            objPlano.setCodPlano(rs.getInt("CODPLANO"));
            objPlano.setNomePlano(rs.getString("NOMEPLANO"));
            objPlano.setTipoPlano(rs.getString("TIPOPLANO"));
            objPlano.setValorPlano(rs.getDouble("VALORPLANO"));
            return objPlano;
        }
    }

    public List<Plano> buscarTodosPlanos() {
        String sql = "SELECT * FROM PLANO";
        return buscarTodos(sql, new PlanoRowMapper());
    }

    public Plano buscarPlanoPorId(int id) {
        String sql = "SELECT * FROM PLANO WHERE CODPLANO=?";
        return buscarPorId(sql, new PlanoRowMapper(), id);
    }

    // NOVO: buscar filtrando por tipo
    public List<Plano> buscarPlanosPorTipo(String tipo) {
        if (tipo == null || tipo.isEmpty()) {
            String sql = "SELECT * FROM PLANO";
            return buscarTodos(sql, new PlanoRowMapper());
        } else {
            String sql = "SELECT * FROM PLANO WHERE TIPOPLANO=?";
            return buscarPorParametros(sql, new PlanoRowMapper(), tipo);
        }
    }

    // NOVO: buscar tipos distintos de plano (retorna List<String>)
    public List<String> buscarTiposPlano() {
        List<String> tipos = new ArrayList<>();
        String sql = "SELECT DISTINCT TIPOPLANO FROM PLANO ORDER BY TIPOPLANO";

        // ConnectionFactory deve existir no mesmo pacote (conforme seu GenericoDAO)
        try (Connection con = ConnectionFactory.getInstance().getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                tipos.add(rs.getString("TIPOPLANO"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tipos;
    }
}
