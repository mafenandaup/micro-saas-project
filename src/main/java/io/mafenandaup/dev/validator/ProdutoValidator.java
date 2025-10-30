package io.mafenandaup.dev.validator;

import io.mafenandaup.dev.exceptions.DuplicateRegistryException;
import io.mafenandaup.dev.model.Produto;
import io.mafenandaup.dev.model.Usuario;
import io.mafenandaup.dev.repository.ProdutoRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ProdutoValidator {

    private ProdutoRepository repository;

    public ProdutoValidator(ProdutoRepository repository) {
        this.repository = repository;
    }

    public void validarRegistro(Produto produto)  {
        if (produtoExists(produto)){
            throw new DuplicateRegistryException("O produto informado já existe. Tente novamente.");
        }
    }

    private boolean produtoExists(Produto produto){
        Optional<Produto> produtoFound = repository.findByNomeAndValorUnitarioAndCategoriaProduto(
                produto.getNome(),
                produto.getValorUnitario(),
                produto.getCategoriaProduto()
        );
        if (produto.getId() ==null){
            return produtoFound.isPresent();
        }
        return  produtoFound.isPresent() && !produtoFound.get().getId().equals(produto.getId());
    }
}

