package io.mafenandaup.dev.controller;

import io.mafenandaup.dev.dto.PedidoDTO;
import io.mafenandaup.dev.dto.ProdutoDTO;
import io.mafenandaup.dev.model.Pedido;
import io.mafenandaup.dev.model.Produto;
import io.mafenandaup.dev.model.Usuario;
import io.mafenandaup.dev.service.PedidoService;
import io.mafenandaup.dev.service.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {


    private PedidoService service;

    public PedidoController(PedidoService service) {
        this.service = service;
    }

    @GetMapping
    public List<Pedido> getPedidos(){
        return service.getPedidos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pedido> findById(@PathVariable String id){

        var idPedido = UUID.fromString(id);
        Optional<Pedido> pedidoOptional = service.obtainById(idPedido);
        Pedido pedido = pedidoOptional.get();
        return ResponseEntity.ok(pedido);
    }

    @PostMapping
    public ResponseEntity<Produto> saveUser(@RequestBody @Valid ProdutoDTO produto){
        var produtoEntity = produto.mapAttributesProduto();
        try {
            service.saveProduct(produtoEntity);
            return new ResponseEntity<>(produtoEntity, HttpStatus.CREATED);
        }catch (IllegalArgumentException e){ // ALTERAR ESSES EXECPTIONS PRA ALGO MAIS PERSONALIZADO
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Produto> deleteUser(@PathVariable String id) {
        try {
            var idProduto = UUID.fromString(id);
            Optional<Produto> produtoOptional = service.obtainById(idProduto);

            if (produtoOptional.isEmpty()) {
                return ResponseEntity.notFound().build();
            }

            service.deleteProduto(produtoOptional.get());
            return ResponseEntity.noContent().build();

        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<PedidoDTO> updateUser(@PathVariable String id, @RequestBody ProdutoDTO dto) {
        try {
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
        } catch (IllegalArgumentException e) {
            throw new RuntimeException(e.getMessage()); // colocar mensagem customizada de erro depois
        }
    }
}
