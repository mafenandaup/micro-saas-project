package io.mafenandaup.dev.service;

import io.mafenandaup.dev.model.ItemPedido;
import io.mafenandaup.dev.model.Produto;
import io.mafenandaup.dev.repository.ItemPedidoRepository;
import io.mafenandaup.dev.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ItemPedidoService {

    private ItemPedidoRepository repository;

    public ItemPedidoService(ItemPedidoRepository repository) {
        this.repository = repository;
    }


    public List<ItemPedido> getItensPedido(){
        return repository.findAll();
    }

    public ItemPedido saveItensPedido(ItemPedido item){
        return repository.save(item);
    }

    public Optional<ItemPedido> obtainById(UUID id) {
        return repository.findById(id);
    }
}
