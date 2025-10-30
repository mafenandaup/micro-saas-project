package io.mafenandaup.dev.validator;

import io.mafenandaup.dev.exceptions.DuplicateRegistryException;
import io.mafenandaup.dev.model.ItemPedido;
import io.mafenandaup.dev.model.Pedido;
import io.mafenandaup.dev.repository.ItemPedidoRepository;
import jakarta.persistence.Column;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ItemPedidoValidator {

    private ItemPedidoRepository repository;

    public ItemPedidoValidator(ItemPedidoRepository repository) {
        this.repository = repository;
    }

    public void validarRegistro(ItemPedido item) {
        if (itemPedidoExists(item)){
            throw new DuplicateRegistryException("O o item registrado já existe no pedido. tente novamente.");
        }
    }

    private boolean itemPedidoExists(ItemPedido item){
        Optional<ItemPedido> itemPedidoFound = repository.findByPedidoAndProdutoAndQuantidade(
                item.getPedido(),
                item.getProduto(),
                item.getQuantidade()
        );
        if (item.getId() ==null){
            return itemPedidoFound.isPresent();
        }
        return  itemPedidoFound.isPresent() && !itemPedidoFound.get().getId().equals(item.getId());
    }
}
