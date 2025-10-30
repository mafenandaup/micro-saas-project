package io.mafenandaup.dev.service;

import io.mafenandaup.dev.exceptions.InvalidArgsException;
import io.mafenandaup.dev.model.Pedido;
import io.mafenandaup.dev.model.Produto;
import io.mafenandaup.dev.model.Usuario;
import io.mafenandaup.dev.repository.ProdutoRepository;
import io.mafenandaup.dev.validator.ProdutoValidator;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProdutoService {


    private ProdutoRepository repository;
    private ProdutoValidator validator;

    public ProdutoService(ProdutoRepository repository) {
        this.repository = repository;
    }


    public List<Produto> getProducts(){
        return repository.findAll();
    }

    public Produto saveProduct(Produto produto){
        validator.validarRegistro(produto);
        return repository.save(produto);
    }

    public Optional<Produto> obtainById(UUID id) {
        var produto = repository.findById(id);
        if (produto.isEmpty()){
            throw new InvalidArgsException("Produto não encontrado/registrado. Tente novamente");
        }
        return repository.findById(id);
    }

    public void deleteProduto(Produto produto) {
        repository.delete(produto);
    }

    public void alterarProduto( Produto produto){
        if (produto.getId() == null){
            throw new InvalidArgsException("Produto não encontrado/registrado. Tente novamente");
        }
        validator.validarRegistro(produto);
        repository.save(produto);
    }
}
