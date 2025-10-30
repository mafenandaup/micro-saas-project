package io.mafenandaup.dev.service;

import io.mafenandaup.dev.exceptions.InvalidArgsException;
import io.mafenandaup.dev.model.Pedido;
import io.mafenandaup.dev.model.Produto;
import io.mafenandaup.dev.model.Usuario;
import io.mafenandaup.dev.repository.PedidoRepository;
import io.mafenandaup.dev.repository.ProdutoRepository;
import io.mafenandaup.dev.validator.PedidoValidator;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
public class PedidoService {

    private PedidoRepository repository;
    private PedidoValidator validator;

    public PedidoService(PedidoRepository repository) {
        this.repository = repository;
    }


    public List<Pedido> getPedidos(){
        return repository.findAll();
    }

    public Pedido savePedido (Pedido pedido){
        validator.validarRegistro(pedido);
        return repository.save(pedido);
    }

    public Optional<Pedido> obtainById(UUID id) {
        var pedido = repository.findById(id);
        if (pedido.isEmpty()){
            throw new InvalidArgsException("Pedido não encontrado/registrado. Tente novamente");
        }
        return pedido;
    }

    public void deletePedido(Pedido pedido) {
        repository.delete(pedido);
    }

    public void alterarPedido( Pedido pedido){
        if (pedido.getId() == null){
            throw new InvalidArgsException("Pedido não encontrado/registrado. Tente novamente");
        }
        validator.validarRegistro(pedido);
        repository.save(pedido);
    }

}
