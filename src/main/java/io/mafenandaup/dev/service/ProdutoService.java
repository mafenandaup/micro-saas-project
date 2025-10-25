package io.mafenandaup.dev.service;

import io.mafenandaup.dev.model.Pedido;
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

    public void deleteProduto(Produto produto) {
        repository.delete(produto);
    }

    public void alterarProduto( Produto produto){
        if (produto.getId() == null){
            throw new IllegalArgumentException("Usuário não encontrado/registrado. Tente novamente");
        }
        // adicionar validações de campo com o validator aqui depois, além de verificação de registro nos outros métodos,etc
        repository.save(produto);
    }
}
