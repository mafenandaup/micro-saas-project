package io.mafenandaup.dev.controller;

import io.mafenandaup.dev.dto.ProdutoDTO;
import io.mafenandaup.dev.dto.UsuarioDTO;
import io.mafenandaup.dev.model.Pedido;
import io.mafenandaup.dev.model.Produto;
import io.mafenandaup.dev.model.Usuario;
import io.mafenandaup.dev.service.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
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
    public List<Produto> getProducts() {
        return service.getProducts();
    }


    @GetMapping("/{id}")
    public ResponseEntity<ProdutoDTO> findProductById(@PathVariable String id){

        var idProduto = UUID.fromString(id);
        Optional<Produto> produtoOptional = service.obtainById(idProduto);

        if (produtoOptional.isPresent()){
            Produto produto = produtoOptional.get();
            ProdutoDTO dto = new ProdutoDTO(produto.getId(),produto.getNome(),produto.getValorUnitario(),produto.getEstoque(),produto.getCategoriaProduto(),produto.getDisponivel());
            return ResponseEntity.ok(dto);
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Produto> saveProduto(@RequestBody @Valid ProdutoDTO produto){
        var produtoEntity = produto.mapAttributesProduto();
        try {
            service.saveProduct(produtoEntity);
            return new ResponseEntity<>(produtoEntity, HttpStatus.CREATED);
        }catch (IllegalArgumentException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Produto> deleteProduto(@PathVariable String id) {

            var idProduto = UUID.fromString(id);
            Optional<Produto> produtoOptional = service.obtainById(idProduto);

            if (produtoOptional.isEmpty()) {
                return ResponseEntity.notFound().build();
            }

            service.deleteProduto(produtoOptional.get());
            return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutoDTO> updateUser(@PathVariable String id, @RequestBody ProdutoDTO dto) {

            var idProduto = UUID.fromString(id);
            Optional<Produto> produtoOptional = service.obtainById(idProduto);

            if (produtoOptional.isEmpty()) {
                return ResponseEntity.notFound().build();
            }

            var produto = produtoOptional.get();
           produto.setNome(dto.nome());
           produto.setCategoriaProduto(dto.categoriaProduto());
           produto.setEstoque(dto.estoque());
           produto.setDisponivel(dto.disponivel());
           produto.setValorUnitario(dto.valorUnitario());

            service.alterarProduto(produto);

            return ResponseEntity.ok(dto);

    }

}
