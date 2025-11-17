package com.mycompany.hospital.dao;

import com.mycompany.hospital.dao.entidade.Internacao;
import com.mycompany.hospital.dao.entidade.Paciente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InternacaoDAO extends GenericoDAO<Internacao> {

    public void salvar(Internacao objInternacao) {
        String sql = "INSERT INTO internacao(dataHora, sala, descricao, pacienteInternacao, funcionarioInternacao) VALUES(?,?,?,?,?)";
        save(sql, objInternacao.getDataHora(), objInternacao.getSala(), objInternacao.getDescricao(),
                objInternacao.getPacienteInternacao().getCodPaciente(),
                objInternacao.getFuncionarioInternacao().getCodFunc());
    }

    public void alterar(Internacao objInternacao) {
        String sql = "UPDATE internacao SET dataHora=?, sala=?, descricao=?, pacienteInternacao=?, funcionarioInternacao=? WHERE codInternacao=?";
        save(sql, objInternacao.getDataHora(), objInternacao.getSala(), objInternacao.getDescricao(),
                objInternacao.getPacienteInternacao().getCodPaciente(),
                objInternacao.getFuncionarioInternacao().getCodFunc(),
                objInternacao.getCodInternacao());
    }

    public void excluir(Internacao objInternacao) {
        String sql = "DELETE FROM internacao WHERE codInternacao=?";
        save(sql, objInternacao.getCodInternacao());
    }

    private static class InternacaoRowMapper implements RowMapper<Internacao> {
        PacienteDAO objpacienteDao = new PacienteDAO();
        FuncionarioDAO objfuncionarioDao = new FuncionarioDAO();

        @Override
        public Internacao mapRow(ResultSet rs) throws SQLException {
            Internacao objInternacao = new Internacao();
            objInternacao.setCodInternacao(rs.getInt("codInternacao"));
            objInternacao.setDataHora(rs.getString("dataHora"));
            objInternacao.setSala(rs.getInt("sala"));
            objInternacao.setDescricao(rs.getString("descricao"));
            objInternacao.setPacienteInternacao(objpacienteDao.buscarPacientePorId(rs.getInt("pacienteInternacao")));
            objInternacao.setFuncionarioInternacao(objfuncionarioDao.buscarFuncionarioPorId(rs.getInt("funcionarioInternacao")));
            return objInternacao;
        }
    }

    public List<Internacao> buscarTodasInternacoes() {
        String sql = "SELECT * FROM internacao";
        return buscarTodos(sql, new InternacaoRowMapper());
    }

    public Internacao buscarInternacaoPorId(int id) {
        String sql = "SELECT * FROM internacao WHERE codInternacao=?";
        return buscarPorId(sql, new InternacaoRowMapper(), id);
    }

    public List<Internacao> buscarPorPaciente(int idPaciente) {
        List<Internacao> internacoes = new ArrayList<>();
        String sql = "SELECT i.codInternacao, i.dataHora, i.sala, i.descricao, " +
                     "p.codPaciente, p.nomePaciente " +
                     "FROM internacao i " +
                     "INNER JOIN paciente p ON i.pacienteInternacao = p.codPaciente " +
                     "WHERE p.codPaciente = ?";

        try (Connection con = ConnectionFactory.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idPaciente);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Internacao internacao = new Internacao();
                    internacao.setCodInternacao(rs.getInt("codInternacao"));
                    internacao.setDataHora(rs.getString("dataHora"));
                    internacao.setSala(rs.getInt("sala"));
                    internacao.setDescricao(rs.getString("descricao"));

                    Paciente paciente = new Paciente();
                    paciente.setCodPaciente(rs.getInt("codPaciente"));
                    paciente.setNomePaciente(rs.getString("nomePaciente"));
                    internacao.setPacienteInternacao(paciente);

                    internacoes.add(internacao);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return internacoes;
    }

    // 🔹 NOVOS MÉTODOS

    public List<Integer> buscarSalasAtivas() {
        List<Integer> salas = new ArrayList<>();
        String sql = "SELECT DISTINCT sala FROM internacao WHERE sala IS NOT NULL ORDER BY sala";
        try (Connection con = ConnectionFactory.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                salas.add(rs.getInt("sala"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return salas;
    }

    public List<Internacao> buscarInternacoesPorSala(int sala) {
        String sql = "SELECT * FROM internacao WHERE sala=?";
        return buscarPorParametros(sql, new InternacaoRowMapper(), sala);
    }
}
