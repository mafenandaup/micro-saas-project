package io.mafenandaup.dev.dto;

import io.mafenandaup.dev.model.CategoriaProduto;
import io.mafenandaup.dev.model.Role;
import io.mafenandaup.dev.model.TipoEmpresa;
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

                           @Enumerated(EnumType.STRING)
                           Integer estoque,

                           @Enumerated(EnumType.STRING)
                           @NotBlank(message = "Campo incompleto. tente novamente")
                           CategoriaProduto categoriaProduto,

                           @Enumerated(EnumType.STRING)
                           @NotEmpty(message = "Campo incompleto. tente novamente")
                           Boolean disponivel
) {
}
