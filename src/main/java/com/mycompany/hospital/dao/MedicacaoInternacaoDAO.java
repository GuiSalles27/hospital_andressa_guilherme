package com.mycompany.hospital.dao;

import com.mycompany.hospital.dao.entidade.Internacao;
import com.mycompany.hospital.dao.entidade.Medicacao;
import com.mycompany.hospital.dao.entidade.MedicacaoInternacao;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * DAO para a entidade MedicacaoInternacao.
 * Gerencia a persistência dos dados da tabela de associação medicacao_has_internacao.
 * * @author Andressa & Guilherme
 */
public class MedicacaoInternacaoDAO extends GenericoDAO<MedicacaoInternacao> {

    /**
     * Salva um registro de medicação associado a uma internação.
     * O valor total é calculado com base no preço do medicamento e na quantidade.
     *
     * @param obj O objeto MedicacaoInternacao a ser salvo.
     */
    public void salvar(MedicacaoInternacao obj) {
        String sql = "INSERT INTO medicacao_has_internacao (medicacao_codRemedio, internacao_codInternacao, quantidadeRemedio, valor) VALUES (?, ?, ?, ?)";
        // Calcula o valor total no momento da "venda" para manter o histórico de preços
        Double valorNoMomento = obj.getMedicacao().getValorRemedio() * obj.getQuantidadeRemedio();
        save(sql, obj.getMedicacao().getCodRemedio(), obj.getInternacao().getCodInternacao(), obj.getQuantidadeRemedio(), valorNoMomento);
    }

    /**
     * Altera um registro existente.
     *
     * @param obj O objeto MedicacaoInternacao a ser alterado.
     */
    public void alterar(MedicacaoInternacao obj) {
        String sql = "UPDATE medicacao_has_internacao SET medicacao_codRemedio = ?, internacao_codInternacao = ?, quantidadeRemedio = ?, valor = ? WHERE codMedicacaoInternacao = ?";
        Double valorNoMomento = obj.getMedicacao().getValorRemedio() * obj.getQuantidadeRemedio();
        save(sql, obj.getMedicacao().getCodRemedio(), obj.getInternacao().getCodInternacao(), obj.getQuantidadeRemedio(), valorNoMomento, obj.getCodMedicacaoInternacao());
    }

    /**
     * Exclui um registro pelo seu ID.
     *
     * @param obj O objeto MedicacaoInternacao a ser excluído.
     */
    public void excluir(MedicacaoInternacao obj) {
        String sql = "DELETE FROM medicacao_has_internacao WHERE codMedicacaoInternacao = ?";
        save(sql, obj.getCodMedicacaoInternacao());
    }

    /**
     * Busca todos os registros de medicação por internação.
     *
     * @return Uma lista de objetos MedicacaoInternacao.
     */
    public List<MedicacaoInternacao> buscarTodos() {
        String sql = "SELECT * FROM medicacao_has_internacao";
        return buscarTodos(sql, new MedicacaoInternacaoRowMapper());
    }

    /**
     * Busca um registro específico pelo seu ID.
     *
     * @param id O ID do registro a ser buscado.
     * @return O objeto MedicacaoInternacao encontrado, ou null se não existir.
     */
    public MedicacaoInternacao buscarPorId(int id) {
        String sql = "SELECT * FROM medicacao_has_internacao WHERE codMedicacaoInternacao = ?";
        return buscarPorId(sql, new MedicacaoInternacaoRowMapper(), id);
    }

    /**
     * Classe interna para mapear as linhas do ResultSet para objetos MedicacaoInternacao.
     */
    private static class MedicacaoInternacaoRowMapper implements RowMapper<MedicacaoInternacao> {
        // Instâncias de outros DAOs para buscar objetos relacionados
        private final MedicacaoDAO medicacaoDAO = new MedicacaoDAO();
        private final InternacaoDAO internacaoDAO = new InternacaoDAO();

        @Override
        public MedicacaoInternacao mapRow(ResultSet rs) throws SQLException {
            MedicacaoInternacao obj = new MedicacaoInternacao();
            
            obj.setCodMedicacaoInternacao(rs.getInt("codMedicacaoInternacao"));
            
            // Busca o objeto Medicacao completo usando o ID da chave estrangeira
            Medicacao medicacao = medicacaoDAO.buscarMedicacaoPorId(rs.getInt("medicacao_codRemedio"));
            obj.setMedicacao(medicacao);
            
            // Busca o objeto Internacao completo usando o ID da chave estrangeira
            Internacao internacao = internacaoDAO.buscarInternacaoPorId(rs.getInt("internacao_codInternacao"));
            obj.setInternacao(internacao);

            obj.setQuantidadeRemedio(rs.getInt("quantidadeRemedio"));
            obj.setValor(rs.getDouble("valor"));

            return obj;
        }
    }
}
