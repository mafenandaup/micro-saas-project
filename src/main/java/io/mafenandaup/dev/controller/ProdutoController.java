package io.mafenandaup.dev.controller;

import io.mafenandaup.dev.model.Pedido;
import io.mafenandaup.dev.model.Produto;
import io.mafenandaup.dev.model.Usuario;
import io.mafenandaup.dev.service.ProdutoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/produto")
public class ProdutoController {



    private ProdutoService service;

    public ProdutoController(ProdutoService service) {
        this.service = service;
    }

    @GetMapping
    public List<Produto> getProducts(){
        return service.getProducts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produto> findUserById(@PathVariable String id){

        var idProduto = UUID.fromString(id);
        Optional<Produto> produtoOptional = service.obtainById(idProduto);
        Produto produto = produtoOptional.get();
        return ResponseEntity.ok(produto);
    }

    @PostMapping
    public Produto saveProduto(Produto produto){
        return service.saveProduct(produto);
    }
}
