package io.mafenandaup.dev.repository;

import io.mafenandaup.dev.model.ItemPedido;
import io.mafenandaup.dev.model.Pedido;
import io.mafenandaup.dev.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ItemPedidoRepository extends JpaRepository<ItemPedido, UUID> {

    Optional<ItemPedido> findByPedidoAndProdutoAndQuantidade(Pedido pedido, Produto produto, Integer quantidade);
}
