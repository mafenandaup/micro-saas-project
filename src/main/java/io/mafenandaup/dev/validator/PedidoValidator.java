package io.mafenandaup.dev.validator;

import io.mafenandaup.dev.exceptions.DuplicateRegistryException;
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

    public void validarRegistro(Pedido pedido)  {
        if (pedidoExists(pedido)){
            throw new DuplicateRegistryException("O pedido informado já existe. Tente novamente.");
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
        return  pedidoFound.isPresent() && !pedidoFound.get().getId().equals(pedido.getId());
    }
}
