package io.mafenandaup.dev.controller;

import io.mafenandaup.dev.dto.PedidoDTO;
import io.mafenandaup.dev.dto.ProdutoDTO;
import io.mafenandaup.dev.exceptions.InvalidArgsException;
import io.mafenandaup.dev.model.ItemPedido;
import io.mafenandaup.dev.model.Pedido;
import io.mafenandaup.dev.model.Produto;
import io.mafenandaup.dev.model.Usuario;
import io.mafenandaup.dev.service.PedidoService;
import io.mafenandaup.dev.service.ProdutoService;
import io.mafenandaup.dev.service.UsuarioService;
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
    private UsuarioService usuarioService;

    public PedidoController(PedidoService service, UsuarioService usuarioService) {
        this.service = service;
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public List<Pedido> getPedidos(){
        return service.getPedidos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoDTO> findById(@PathVariable String id){

        var idPedido = UUID.fromString(id);
        Optional<Pedido> pedidoOptional = service.obtainById(idPedido);

        if (pedidoOptional.isPresent()){
            Pedido pedido = pedidoOptional.get();
            PedidoDTO dto = new PedidoDTO(
                    pedido.getId(),
                    pedido.getCodigo(),
                    pedido.getCliente().getId(),
                    pedido.getRepresentante().getId(),
                    pedido.getStatusPedido(),
                    pedido.getStatusPrePedido(),
                    pedido.getValorTotal(),
                    pedido.getLote()
            );
            return ResponseEntity.ok(dto);
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Pedido> savePedido(@RequestBody @Valid PedidoDTO pedido){
        try {
            Usuario cliente = usuarioService.obtainById(pedido.clienteId())
                    .orElseThrow(() -> new InvalidArgsException("Pedido não encontrado"));
            Usuario representante = usuarioService.obtainById(pedido.representanteId())
                    .orElseThrow(() -> new InvalidArgsException("Produto não encontrado"));

            Pedido pedidoMap = pedido.mapAttributesPedido(cliente, representante);

            service.savePedido(pedidoMap);
            return new ResponseEntity<>(pedidoMap, HttpStatus.CREATED);
        } catch (InvalidArgsException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Pedido> deletePedido(@PathVariable String id) {

            var idPedido = UUID.fromString(id);
            Optional<Pedido> pedidoOptional = service.obtainById(idPedido);

            if (pedidoOptional.isEmpty()) {
                return ResponseEntity.notFound().build();
            }

            service.deletePedido(pedidoOptional.get());
            return ResponseEntity.noContent().build();

    }

    @PatchMapping("/{id}")
    public ResponseEntity<PedidoDTO> updateUser(@PathVariable String id, @RequestBody PedidoDTO dto) {

            var idPedido = UUID.fromString(id);
            Optional<Pedido> pedidoOptional = service.obtainById(idPedido);

            if (pedidoOptional.isEmpty()) {
                return ResponseEntity.notFound().build();
            }

            var pedido = pedidoOptional.get();
            pedido.setStatusPedido(dto.statusPedido());
            pedido.setStatusPrePedido(dto.statusPrePedido());


            service.alterarPedido(pedido);

            return ResponseEntity.ok(dto);

    }
}
