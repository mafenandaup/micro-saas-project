package io.mafenandaup.dev.controller;

import io.mafenandaup.dev.model.Pedido;
import io.mafenandaup.dev.model.Produto;
import io.mafenandaup.dev.model.Usuario;
import io.mafenandaup.dev.service.PedidoService;
import io.mafenandaup.dev.service.ProdutoService;
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
    public Pedido saveProduto(Pedido pedido){
        return service.savePedido(pedido);
    }
}
