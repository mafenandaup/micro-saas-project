package io.mafenandaup.dev.validator;

import io.mafenandaup.dev.model.Pedido;
import io.mafenandaup.dev.model.Produto;
import io.mafenandaup.dev.repository.PedidoRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class PedidoValidator {

    private PedidoRepository repository;

    public PedidoValidator(PedidoRepository repository) {
        this.repository = repository;
    }

    public void validarRegistro(Pedido pedido) throws Exception {
        if (pedidoExists(pedido)){
            throw new Exception("O pedido registrado já existe. tente novamente.");
        }
    }

    private boolean pedidoExists(Pedido pedido){
        Optional<Pedido> pedidoFound = repository.findByCodigoAndClienteAndRepresentante(
                pedido.getCodigo(),
                pedido.getCliente(),
                pedido.getRepresentante()
        );
        if (pedido.getId() ==null){
            return pedidoFound.isPresent();
        }
        return !pedido.getId().equals(pedidoFound.get()) && pedidoFound.isPresent();
    }
}
