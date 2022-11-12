package ifpb.edu.br.prontatendimento.model;

import lombok.Data;

public enum Sexo {
    MASCULINO("Masculino"),
    FEMININO("Feminino"),
    OUTROS("Outros");

    private String descricao;

    Sexo(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return this.descricao;
    }
}
