package io.mafenandaup.dev.service;

import io.mafenandaup.dev.model.Produto;
import io.mafenandaup.dev.model.Usuario;
import io.mafenandaup.dev.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProdutoService {


    private ProdutoRepository repository;

    public ProdutoService(ProdutoRepository repository) {
        this.repository = repository;
    }


    public List<Produto> getProducts(){
        return repository.findAll();
    }

    public Produto saveProduct(Produto produto){
        return repository.save(produto);
    }

    public Optional<Produto> obtainById(UUID id) {
        return repository.findById(id);
    }
}
