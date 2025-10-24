package io.mafenandaup.dev.controller;


import io.mafenandaup.dev.model.ItemPedido;
import io.mafenandaup.dev.service.ItemPedidoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/pedidos/{pedidoId}/itens")

public class ItemPedidoController {
    
    private ItemPedidoService service;

    public ItemPedidoController(ItemPedidoService service) {
        this.service = service;
    }

    @GetMapping
    public List<ItemPedido> getItemsPedido(){
        return service.getItensPedido();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemPedido> findById(@PathVariable String id){

        var idItemPedido = UUID.fromString(id);
        Optional<ItemPedido> pedidoOptional = service.obtainById(idItemPedido);
        ItemPedido itemPedido = pedidoOptional.get();
        return ResponseEntity.ok(itemPedido);
    }

    @PostMapping
    public ItemPedido saveItemPedido(ItemPedido item){
        return service.saveItensPedido(item);
    }
}
