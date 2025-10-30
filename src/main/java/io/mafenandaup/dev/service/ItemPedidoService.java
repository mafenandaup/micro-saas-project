package io.mafenandaup.dev.service;

import io.mafenandaup.dev.exceptions.InvalidArgsException;
import io.mafenandaup.dev.model.ItemPedido;
import io.mafenandaup.dev.model.Produto;
import io.mafenandaup.dev.repository.ItemPedidoRepository;
import io.mafenandaup.dev.repository.ProdutoRepository;
import io.mafenandaup.dev.validator.ItemPedidoValidator;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ItemPedidoService {

    private ItemPedidoRepository repository;
    private ItemPedidoValidator validator;

    public ItemPedidoService(ItemPedidoRepository repository) {
        this.repository = repository;
    }


    public List<ItemPedido> getItensPedido(){
        return repository.findAll();
    }

    public ItemPedido saveItensPedido(ItemPedido item){
        validator.validarRegistro(item);
        return repository.save(item);
    }

    public Optional<ItemPedido> obtainById(UUID id) {
        var item = repository.findById(id);
        if (item.isEmpty()) {
            throw new InvalidArgsException("Item não encontrado para o ID informado.");
        }
        return item;
    }

    public void deletarItemPedido(ItemPedido item) {
        repository.delete(item);
    }

    public void alterarItemPedido( ItemPedido item){
        if (item.getId() == null){
            throw new InvalidArgsException("Item do pedido não encontrado/registrado. Tente novamente");
        }
        validator.validarRegistro(item);
        repository.save(item);
    }
}

