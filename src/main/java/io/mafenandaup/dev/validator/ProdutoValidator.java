package io.mafenandaup.dev.validator;

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

    public void validarRegistro(Produto produto) throws Exception {
        if (produtoExists(produto)){
            throw new Exception("O Produto registrado já existe. tente novamente.");
        }
    }

    private boolean produtoExists(Produto produto){
        Optional<Produto> produtoFound = repository.findByNomeAndValorUnitarioAndCategoria(
                produto.getNome(),
                produto.getValorUnitario(),
                produto.getCategoriaProduto()
        );
        if (produto.getId() ==null){
            return produtoFound.isPresent();
        }
        return !produto.getId().equals(produtoFound.get()) && produtoFound.isPresent();
    }
}

