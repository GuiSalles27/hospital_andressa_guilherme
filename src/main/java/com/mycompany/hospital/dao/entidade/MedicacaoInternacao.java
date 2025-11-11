package com.mycompany.hospital.dao.entidade;

/**
 * Representa a associação entre uma medicação e uma internação,
 * incluindo a quantidade e o valor total no momento da dispensação.
 *
 * @author Andressa & Guilherme
 */
public class MedicacaoInternacao {
    private Integer codMedicacaoInternacao;
    private Internacao internacao = new Internacao();
    private Medicacao medicacao = new Medicacao();
    private Integer quantidadeRemedio;
    private Double valor; // Valor total (preço * quantidade) no momento da dispensação

    public Integer getCodMedicacaoInternacao() {
        return codMedicacaoInternacao;
    }

    public void setCodMedicacaoInternacao(Integer codMedicacaoInternacao) {
        this.codMedicacaoInternacao = codMedicacaoInternacao;
    }

    public Internacao getInternacao() {
        return internacao;
    }

    public void setInternacao(Internacao internacao) {
        this.internacao = internacao;
    }

    public Medicacao getMedicacao() {
        return medicacao;
    }

    public void setMedicacao(Medicacao medicacao) {
        this.medicacao = medicacao;
    }

    public Integer getQuantidadeRemedio() {
        return quantidadeRemedio;
    }

    public void setQuantidadeRemedio(Integer quantidadeRemedio) {
        this.quantidadeRemedio = quantidadeRemedio;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }
}
