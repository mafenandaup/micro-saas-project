package io.mafenandaup.dev.repository;

import io.mafenandaup.dev.model.CategoriaProduto;
import io.mafenandaup.dev.model.Produto;
import io.mafenandaup.dev.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

public interface ProdutoRepository extends JpaRepository<Produto, UUID> {

    Optional<Produto> findByNomeAndValorUnitarioAndCategoriaProduto(String nome, BigDecimal valorUnitario, CategoriaProduto categoriaProduto);
}
