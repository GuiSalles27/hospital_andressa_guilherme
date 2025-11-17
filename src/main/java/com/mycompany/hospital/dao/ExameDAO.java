package com.mycompany.hospital.dao;

import com.mycompany.hospital.dao.entidade.Exame;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ExameDAO extends GenericoDAO<Exame> {

    public void salvar(Exame objExame) {
        String sql = "INSERT INTO exame(tipoExame,valor,resultado,pacienteExame,funcionarioExame) VALUES(?,?,?,?,?)";
        save(sql, objExame.getTipoExame(), objExame.getValor(), objExame.getResultado(),
             objExame.getPacienteExame().getCodPaciente(), objExame.getFuncionarioExame().getCodFunc());
    }

    public void alterar(Exame objExame) {
        String sql = "UPDATE exame SET tipoExame=?, valor=?,resultado=?, pacienteExame=?,funcionarioExame=? WHERE codExame=?";
        save(sql, objExame.getTipoExame(), objExame.getValor(), objExame.getResultado(),
             objExame.getPacienteExame().getCodPaciente(), objExame.getFuncionarioExame().getCodFunc(),
             objExame.getCodExame());
    }

    public void excluir(Exame objExame) {
        String sql = "DELETE FROM exame WHERE codExame=?";
        save(sql, objExame.getCodExame());
    }

    private static class ExameRowMapper implements RowMapper<Exame> {
        FuncionarioDAO objFuncionarioDao = new FuncionarioDAO();
        PacienteDAO objPacienteDao = new PacienteDAO();

        @Override
        public Exame mapRow(ResultSet rs) throws SQLException {
            Exame objExame = new Exame();
            objExame.setCodExame(rs.getInt("codExame"));
            objExame.setTipoExame(rs.getString("tipoExame"));
            objExame.setValor(rs.getDouble("valor"));
            objExame.setResultado(rs.getString("resultado"));

            objExame.setPacienteExame(objPacienteDao.buscarPacientePorId(rs.getInt("pacienteExame")));
            objExame.setFuncionarioExame(objFuncionarioDao.buscarFuncionarioPorId(rs.getInt("funcionarioExame")));

            return objExame;
        }
    }

    public List<Exame> buscarTodosExame() {
        String sql = "SELECT * FROM exame";
        return buscarTodos(sql, new ExameRowMapper());
    }

    public Exame buscarExamePorId(int id) {
        String sql = "SELECT * FROM exame WHERE codExame=?";
        return buscarPorId(sql, new ExameRowMapper(), id);
    }

    // 🔹 Novo método para múltiplos pacientes
    public List<Exame> buscarExamesPorPacientes(List<Integer> codPacientes) {
        if (codPacientes == null || codPacientes.isEmpty()) {
            return new ArrayList<>();
        }

        String placeholders = codPacientes.stream()
                .map(c -> "?")
                .collect(Collectors.joining(","));

        String sql = "SELECT * FROM exame WHERE pacienteExame IN (" + placeholders + ")";

        return buscarPorParametros(sql, new ExameRowMapper(), codPacientes.toArray());
    }
}
