package io.mafenandaup.dev.dto;

import io.mafenandaup.dev.model.*;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record ProdutoDTO(  java.util.UUID id,
                           @NotBlank(message = "Campo incompleto. tente novamente")
                           String nome,

                           @NotNull(message = "Campo incompleto. tente novamente")
                           BigDecimal valorUnitario,

                           @NotNull(message = "Campo incompleto. tente novamente")
                           Integer estoque,

                           @Enumerated(EnumType.STRING)
                           @NotNull(message = "Campo incompleto. tente novamente")
                           CategoriaProduto categoriaProduto,

                           @NotNull(message = "Campo incompleto. tente novamente")
                           Boolean disponivel
) {

    public Produto mapAttributesProduto(){
        Produto produto = new Produto();
        produto.setNome(this.nome);
        produto.setValorUnitario(this.valorUnitario);
        produto.setEstoque(this.estoque);
        produto.setCategoriaProduto(this.categoriaProduto);
        produto.setDisponivel(this.disponivel);
        return produto;
    }
}
