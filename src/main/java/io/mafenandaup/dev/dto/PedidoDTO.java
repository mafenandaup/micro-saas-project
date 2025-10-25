package io.mafenandaup.dev.dto;

import io.mafenandaup.dev.model.*;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.security.core.parameters.P;

import java.math.BigDecimal;

public record PedidoDTO(
        java.util.UUID id,
        @NotNull(message = "Campo vazio. tente novamente")
        Integer codigo,

        @NotEmpty(message = "Campo incompleto. tente novamente")
        Usuario cliente,

        @NotEmpty(message = "Campo incompleto. tente novamente")
        Usuario representante,


        @NotEmpty(message = "Campo incompleto. tente novamente")
        @Enumerated(EnumType.STRING)
        StatusPedido statusPedido,

        @Enumerated(EnumType.STRING)
        StatusPrePedido statusPrePedido,

        @NotNull(message = "Campo incompleto. tente novamente")
        BigDecimal valorTotal,

        @NotNull(message = "Campo vazio. tente novamente")
        Integer lote

) {

    public Pedido mapAttributesPedido(){
        Pedido pedido = new Pedido();
        pedido.setCodigo(this.codigo);
        pedido.setCliente(this.cliente);
        pedido.setRepresentante(this.representante);
        pedido.setStatusPedido(this.statusPedido);
        pedido.setStatusPrePedido(this.statusPrePedido);
        pedido.setValorTotal(this.valorTotal);
        pedido.setLote(this.lote);
        return pedido;
    }
}

