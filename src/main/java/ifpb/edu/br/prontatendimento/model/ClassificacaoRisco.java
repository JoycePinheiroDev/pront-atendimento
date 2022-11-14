package ifpb.edu.br.prontatendimento.model;

public enum ClassificacaoRisco {
    VERMELHO("Caso de emergência"),
    LARANJA("Caso muito urgente"),
    AMARELO("Caso de urgência"),
    VERDE("Caso pouco urgente"),
    AZUL("Caso não urgente");

    private String descricao;

    ClassificacaoRisco(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return this.descricao;
    }

}
