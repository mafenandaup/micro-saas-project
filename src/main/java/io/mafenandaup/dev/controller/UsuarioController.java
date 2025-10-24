package io.mafenandaup.dev.controller;

import io.mafenandaup.dev.model.Usuario;
import io.mafenandaup.dev.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UsuarioController {

    private final UsuarioService service;

    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    @GetMapping
    public List<Usuario> getUsers(){
        return service.getUsers();
    }

    @PostMapping
    public Usuario saveUser(Usuario user){
        return service.saveUser(user);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> findUserById(@PathVariable String id){

        var idUser = UUID.fromString(id);
        Optional<Usuario> usuarioOptional = service.obtainById(idUser);
        Usuario user = usuarioOptional.get();
        return ResponseEntity.ok(user);
    }


}
