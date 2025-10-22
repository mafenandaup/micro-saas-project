package io.mafenandaup.dev.repository;

import io.mafenandaup.dev.model.Produto;
import io.mafenandaup.dev.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProdutoRepository extends JpaRepository<Produto, UUID> {
}
