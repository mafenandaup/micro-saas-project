package io.mafenandaup.dev.controller;


import io.mafenandaup.dev.dto.ItemPedidoDTO;
import io.mafenandaup.dev.dto.ProdutoDTO;
import io.mafenandaup.dev.model.ItemPedido;
import io.mafenandaup.dev.model.Pedido;
import io.mafenandaup.dev.model.Produto;
import io.mafenandaup.dev.service.ItemPedidoService;
import io.mafenandaup.dev.service.PedidoService;
import io.mafenandaup.dev.service.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/pedidos/{pedidoId}/itens")

public class ItemPedidoController {
    
    private ItemPedidoService service;
    private ProdutoService produtoService;
    private PedidoService pedidoService;

    public ItemPedidoController(ItemPedidoService service, ProdutoService produtoService, PedidoService pedidoService) {
        this.service = service;
        this.produtoService = produtoService;
        this.pedidoService = pedidoService;
    }

    @GetMapping
    public List<ItemPedido> getItemsPedido(){
        return service.getItensPedido();
    }

    @GetMapping("/{itemid}")
    public ResponseEntity<ItemPedidoDTO> findById(@PathVariable String itemid){

        var idItemPedido = UUID.fromString(itemid);
        Optional<ItemPedido> itemPedidoOptional= service.obtainById(idItemPedido);

        if (itemPedidoOptional.isPresent()) {
            ItemPedido item = itemPedidoOptional.get();
            ItemPedidoDTO dto = new ItemPedidoDTO(
                    item.getId(),
                    item.getPedido().getId(),
                    item.getProduto().getId(),
                    item.getValorUnitario(),
                    item.getQuantidade(),
                    item.getSubtotal()
            );
            return ResponseEntity.ok(dto);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<ItemPedido> saveItemPedido(@RequestBody @Valid ItemPedidoDTO itensDTO){
        try {
            Pedido pedido = pedidoService.obtainById(itensDTO.pedidoId())
                    .orElseThrow(() -> new IllegalArgumentException("Pedido não encontrado"));
            Produto produto = produtoService.obtainById(itensDTO.produtoId())
                    .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado"));

            ItemPedido item = itensDTO.mapAttributesItemPedido(pedido, produto);

            service.saveItensPedido(item);
            return new ResponseEntity<>(item, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{itemid}")
    public ResponseEntity<ItemPedido> deleteItemPedido(@PathVariable String itemid) {

            var idItemPedido = UUID.fromString(itemid);
            Optional<ItemPedido> itemPedidoOptional= service.obtainById(idItemPedido);

            if (itemPedidoOptional.isEmpty()) {
                return ResponseEntity.notFound().build();
            }

            service.deletarItemPedido(itemPedidoOptional.get());
            return ResponseEntity.noContent().build();

    }

}
