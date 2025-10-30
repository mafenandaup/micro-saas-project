package io.mafenandaup.dev.controller;

import io.mafenandaup.dev.dto.UsuarioDTO;
import io.mafenandaup.dev.exceptions.InvalidArgsException;
import io.mafenandaup.dev.model.Usuario;
import io.mafenandaup.dev.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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
    public ResponseEntity<Usuario> saveUser(@RequestBody @Valid UsuarioDTO user){
        var userEntity = user.mapAttributesUser();
            service.saveUser(userEntity);
            return ResponseEntity.status(HttpStatus.CREATED).body(userEntity);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> findUserById(@PathVariable String id){

        var idUser = UUID.fromString(id);
        Optional<Usuario> usuarioOptional = service.obtainById(idUser);

        if (usuarioOptional.isPresent()){
            Usuario user = usuarioOptional.get();
            UsuarioDTO dto = new UsuarioDTO(user.getId(), user.getNome(), user.getEmail(), user.getSenha(),user.getRole(), user.getAtivo());
            return ResponseEntity.ok(dto);
        }

        return ResponseEntity.notFound().build();
    }


    @PutMapping("/{id}")
    public ResponseEntity<UsuarioDTO> updateUser(@PathVariable String id, @RequestBody UsuarioDTO dto) {
            var idUser = UUID.fromString(id);
            Optional<Usuario> userOptional = service.obtainById(idUser);

            if (userOptional.isEmpty()) {
                return ResponseEntity.notFound().build();
            }

            var user = userOptional.get();
            user.setAtivo(dto.ativo());
            user.setRole(dto.role());
            user.setNome(dto.nome());
            user.setEmail(dto.email());

            service.alterarUsuario(user);

            return ResponseEntity.ok(dto);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Usuario> deleteUser(@PathVariable String id) {
            var idUser = UUID.fromString(id);
            Optional<Usuario> userOptional = service.obtainById(idUser);

            if (userOptional.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            service.deleteUser(userOptional.get());
            return ResponseEntity.noContent().build();
        }
    }

