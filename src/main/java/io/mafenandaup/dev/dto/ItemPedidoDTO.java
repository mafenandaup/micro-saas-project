package io.mafenandaup.dev.dto;

import io.mafenandaup.dev.model.Pedido;
import io.mafenandaup.dev.model.Produto;
import io.mafenandaup.dev.model.Role;
import io.mafenandaup.dev.model.TipoEmpresa;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record ItemPedidoDTO(
        java.util.UUID id,
        @NotEmpty(message = "Campo incompleto. tente novamente")
        Pedido pedido,

        @NotEmpty(message = "Campo incompleto. tente novamente")
        Produto produto,

        @NotNull(message = "Campo incompleto. tente novamente")
        BigDecimal valorUnitario,

        @NotNull(message = "Campo incompleto. tente novamente")
        Integer quantidade,

        @NotNull(message = "Campo incompleto. tente novamente")
        BigDecimal subtotal

        ) {
}
