package io.mafenandaup.dev.repository;

import io.mafenandaup.dev.model.Pedido;
import io.mafenandaup.dev.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

public interface PedidoRepository extends JpaRepository<Pedido, UUID> {

    Optional<Pedido> findByCodigoAndClienteAndRepresentante(Integer codigo, Usuario cliente, Usuario representante);
}
