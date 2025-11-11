package com.mycompany.hospital.dao;

import com.mycompany.hospital.dao.entidade.Paciente;
import com.mycompany.hospital.dao.entidade.Plano;
import com.mycompany.hospital.servico.ConverteData;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class PacienteDAO extends GenericoDAO<Paciente> {

    public void salvar(Paciente objPaciente) {
        String sql = "INSERT INTO PACIENTE(nomePaciente,cpf,dataNascimento,filiacao,rg,endereco,bairro,cidade,cep,uf,telefone,planoPaciente) VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
        save(sql,
                objPaciente.getNomePaciente(),
                objPaciente.getCpf(),
                objPaciente.getDataNascimento(),
                objPaciente.getFiliacao(),
                objPaciente.getRg(),
                objPaciente.getEndereco(),
                objPaciente.getBairro(),
                objPaciente.getCidade(),
                objPaciente.getCep(),
                objPaciente.getUf(),
                objPaciente.getTelefone(),
                objPaciente.getPlanoPaciente().getCodPlano());
    }

    public void alterar(Paciente objPaciente) {
        String sql = "UPDATE PACIENTE SET nomePaciente=?, cpf=?, dataNascimento=?, filiacao=?, rg=?, endereco=?, bairro=?, cidade=?, cep=?, uf=?, telefone=?, planoPaciente=? WHERE codPaciente=?";
        save(sql,
                objPaciente.getNomePaciente(),
                objPaciente.getCpf(),
                objPaciente.getDataNascimento(),
                objPaciente.getFiliacao(),
                objPaciente.getRg(),
                objPaciente.getEndereco(),
                objPaciente.getBairro(),
                objPaciente.getCidade(),
                objPaciente.getCep(),
                objPaciente.getUf(),
                objPaciente.getTelefone(),
                objPaciente.getPlanoPaciente().getCodPlano(),
                objPaciente.getCodPaciente());
    }

    public void excluir(Paciente objPaciente) {
        String sql = "DELETE FROM PACIENTE WHERE codPaciente=?";
        save(sql, objPaciente.getCodPaciente());
    }

    private static class PacienteRowMapper implements RowMapper<Paciente> {
        ConverteData converte = new ConverteData();

        @Override
        public Paciente mapRow(ResultSet rs) throws SQLException {
            Paciente objPaciente = new Paciente();
            objPaciente.setCodPaciente(rs.getInt("codPaciente"));
            objPaciente.setNomePaciente(rs.getString("nomePaciente"));
            objPaciente.setCpf(rs.getString("cpf"));
            objPaciente.setDataNascimento(converte.converteCalendario(rs.getDate("dataNascimento")));
            objPaciente.setFiliacao(rs.getString("filiacao"));
            objPaciente.setRg(rs.getString("rg"));
            objPaciente.setEndereco(rs.getString("endereco"));
            objPaciente.setBairro(rs.getString("bairro"));
            objPaciente.setCidade(rs.getString("cidade"));
            objPaciente.setCep(rs.getString("cep"));
            objPaciente.setUf(rs.getString("uf"));
            objPaciente.setTelefone(rs.getString("telefone"));

            // Plano vindo do JOIN
            Plano plano = new Plano();
            plano.setCodPlano(rs.getInt("codPlano"));
            plano.setNomePlano(rs.getString("nomePlano"));
            plano.setTipoPlano(rs.getString("tipoPlano"));
            plano.setValorPlano(rs.getDouble("valorPlano"));
            objPaciente.setPlanoPaciente(plano);

            return objPaciente;
        }
    }

    // Agora sempre faz JOIN para trazer informações do plano
    public List<Paciente> buscarTodosPaciente() {
        String sql = "SELECT p.*, pl.codPlano, pl.nomePlano, pl.tipoPlano, pl.valorPlano " +
                     "FROM PACIENTE p " +
                     "LEFT JOIN PLANO pl ON p.planoPaciente = pl.codPlano";
        return buscarTodos(sql, new PacienteRowMapper());
    }

    public Paciente buscarPacientePorId(int id) {
        String sql = "SELECT p.*, pl.codPlano, pl.nomePlano, pl.tipoPlano, pl.valorPlano " +
                     "FROM PACIENTE p " +
                     "LEFT JOIN PLANO pl ON p.planoPaciente = pl.codPlano " +
                     "WHERE p.codPaciente=?";
        return buscarPorId(sql, new PacienteRowMapper(), id);
    }

    // Caso precise listar apenas pacientes com plano (INNER JOIN)
    public List<Paciente> buscarTodosPacientePlano() {
        String sql = "SELECT p.*, pl.codPlano, pl.nomePlano, pl.tipoPlano, pl.valorPlano " +
                     "FROM PACIENTE p " +
                     "INNER JOIN PLANO pl ON p.planoPaciente = pl.codPlano";
        return buscarTodos(sql, new PacienteRowMapper());
    }
}
