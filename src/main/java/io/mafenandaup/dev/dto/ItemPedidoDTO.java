package io.mafenandaup.dev.dto;

import io.mafenandaup.dev.model.*;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record ItemPedidoDTO(
        java.util.UUID id,
        @NotNull(message = "Campo incompleto. tente novamente")
        java.util.UUID pedidoId,

        @NotNull(message = "Campo incompleto. tente novamente")
        java.util.UUID produtoId,

        @NotNull(message = "Campo incompleto. tente novamente")
        BigDecimal valorUnitario,

        @NotNull(message = "Campo incompleto. tente novamente")
        Integer quantidade,

        @NotNull(message = "Campo incompleto. tente novamente")
        BigDecimal subtotal

) {


    public ItemPedido mapAttributesItemPedido(Pedido pedido, Produto produto) {
        ItemPedido item = new ItemPedido();
        item.setPedido(pedido);
        item.setProduto(produto);
        item.setValorUnitario(this.valorUnitario);
        item.setQuantidade(this.quantidade);
        item.setSubtotal(this.subtotal);
        return item;
    }
}

