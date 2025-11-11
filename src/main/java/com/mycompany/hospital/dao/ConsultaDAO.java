package com.mycompany.hospital.dao;

import com.mycompany.hospital.dao.entidade.Consulta;
import com.mycompany.hospital.dao.entidade.Paciente;
import com.mycompany.hospital.dao.entidade.Funcionario;
import com.mycompany.hospital.servico.ConverteData;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ConsultaDAO extends GenericoDAO<Consulta> {

    private final ConverteData converte = new ConverteData();

    public void salvar(Consulta objConsulta) {
        if (objConsulta.getPacienteConsulta() == null || objConsulta.getFuncionarioConsulta() == null) {
            throw new IllegalArgumentException("Paciente e Funcionário não podem ser nulos ao salvar consulta.");
        }

        String sql = "INSERT INTO CONSULTA (DIAGNOSTICO, DATACONSULTA, pacienteConsulta, funcionarioConsulta) VALUES (?, ?, ?, ?)";
        save(sql,
             objConsulta.getDiagnostico(),
             objConsulta.getDataConsulta(),
             objConsulta.getPacienteConsulta().getCodPaciente(),
             objConsulta.getFuncionarioConsulta().getCodFunc());
    }

    public void alterar(Consulta objConsulta) {
        if (objConsulta.getPacienteConsulta() == null || objConsulta.getFuncionarioConsulta() == null) {
            throw new IllegalArgumentException("Paciente e Funcionário não podem ser nulos ao alterar consulta.");
        }

        String sql = "UPDATE CONSULTA SET DIAGNOSTICO = ?, DATACONSULTA = ?, pacienteConsulta = ?, funcionarioConsulta = ? WHERE CODCONSULTA = ?";
        save(sql,
             objConsulta.getDiagnostico(),
             objConsulta.getDataConsulta(),
             objConsulta.getPacienteConsulta().getCodPaciente(),
             objConsulta.getFuncionarioConsulta().getCodFunc(),
             objConsulta.getCodConsulta());
    }

    public void excluir(Consulta objConsulta) {
        String sql = "DELETE FROM CONSULTA WHERE CODCONSULTA = ?";
        save(sql, objConsulta.getCodConsulta());
    }

    public List<Consulta> buscarTodasConsultas() {
    String sql = "SELECT CODCONSULTA, DIAGNOSTICO, DATACONSULTA, pacienteConsulta, funcionarioConsulta FROM CONSULTA";
    return buscarTodos(sql, new ConsultaRowMapper());
}


    public Consulta buscarConsultaPorId(int id) {
        String sql = "SELECT * FROM CONSULTA WHERE CODCONSULTA = ?";
        return buscarPorId(sql, new ConsultaRowMapper(), id);
    }

    private static class ConsultaRowMapper implements RowMapper<Consulta> {
        ConverteData converte = new ConverteData();
        PacienteDAO objpacienteDao = new PacienteDAO();
        FuncionarioDAO objfuncionarioDao = new FuncionarioDAO();

        @Override
        public Consulta mapRow(ResultSet rs) throws SQLException {
            Consulta objConsulta = new Consulta();

            objConsulta.setCodConsulta(rs.getInt("CODCONSULTA"));
            objConsulta.setDiagnostico(rs.getString("DIAGNOSTICO"));
            objConsulta.setDataConsulta(converte.converteCalendario(rs.getDate("DATACONSULTA")));

            Paciente paciente = objpacienteDao.buscarPacientePorId(rs.getInt("pacienteConsulta"));
            Funcionario funcionario = objfuncionarioDao.buscarFuncionarioPorId(rs.getInt("funcionarioConsulta"));

            objConsulta.setPacienteConsulta(paciente);
            objConsulta.setFuncionarioConsulta(funcionario);

            return objConsulta;
        }
    }
}
