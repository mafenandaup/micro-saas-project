package io.mafenandaup.dev.repository;

import io.mafenandaup.dev.model.Pedido;
import io.mafenandaup.dev.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PedidoRepository extends JpaRepository<Pedido, UUID> {
}
