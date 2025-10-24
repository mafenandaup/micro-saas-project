package io.mafenandaup.dev.controller;

import io.mafenandaup.dev.exceptions.DuplicateRegistryException;
import io.mafenandaup.dev.model.Usuario;
import io.mafenandaup.dev.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
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

    @GetMapping //alterar aqui e o outro get pra configurar um DTO
    public List<Usuario> getUsers(){
        return service.getUsers();
    }

    @PostMapping
    public ResponseEntity<Usuario> saveUser(@RequestBody @Valid Usuario user){
        service.saveUser(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> findUserById(@PathVariable String id){

        var idUser = UUID.fromString(id);
        Optional<Usuario> usuarioOptional = service.obtainById(idUser);
        Usuario user = usuarioOptional.get();
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Usuario> deleteUser(@PathVariable String id) {
        try {
            var idUser = UUID.fromString(id);
            Optional<Usuario> userOptional = service.obtainById(idUser);

            if (userOptional.isEmpty()) {
                return ResponseEntity.notFound().build();
            }

            service.deleteUser(userOptional.get());
            return ResponseEntity.noContent().build();

        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> updateUser(@PathVariable String id, @RequestBody Usuario alterarPraDTO) {
        try {
            var idUser = UUID.fromString(id);
            Optional<Usuario> userOptional = service.obtainById(idUser);

            if (userOptional.isEmpty()) {
                return ResponseEntity.notFound().build();
            }

            var user = userOptional.get();
            user.setAtivo(alterarPraDTO.getAtivo());
            user.setRole(alterarPraDTO.getRole());
            user.setNome(alterarPraDTO.getNome());
            user.setEmail(alterarPraDTO.getEmail());
            user.setSenha(alterarPraDTO.getSenha());
            user.setCreatedAt(alterarPraDTO.getCreatedAt());
            user.setTipo(alterarPraDTO.getTipo());

            service.alterarUsuario(user);

            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            throw new RuntimeException(e.getMessage()); // colocar mensagem customizada de erro depois
        }
    }
}
