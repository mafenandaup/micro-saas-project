package io.mafenandaup.dev.service;

import io.mafenandaup.dev.model.Pedido;
import io.mafenandaup.dev.model.Produto;
import io.mafenandaup.dev.model.Usuario;
import io.mafenandaup.dev.repository.PedidoRepository;
import io.mafenandaup.dev.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
public class PedidoService {

    private PedidoRepository repository;

    public PedidoService(PedidoRepository repository) {
        this.repository = repository;
    }


    public List<Pedido> getPedidos(){
        return repository.findAll();
    }

    public Pedido savePedido (Pedido pedido){
        return repository.save(pedido);
    }

    public Optional<Pedido> obtainById(UUID id) {
        return repository.findById(id);
    }

    public void deletePedido(Pedido pedido) {
        repository.delete(pedido);
    }

    public void alterarPedido( Pedido pedido){
        if (pedido.getId() == null){
            throw new IllegalArgumentException("Usuário não encontrado/registrado. Tente novamente");
        }
        // adicionar validações de campo com o validator aqui depois, além de verificação de registro nos outros métodos,etc
        repository.save(pedido);
    }

}
