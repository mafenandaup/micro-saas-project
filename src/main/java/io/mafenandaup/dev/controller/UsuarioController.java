package io.mafenandaup.dev.controller;

import io.mafenandaup.dev.dto.UsuarioDTO;
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
    public ResponseEntity<UsuarioDTO> findUserById(@PathVariable String id){

        var idUser = UUID.fromString(id);
        Optional<Usuario> usuarioOptional = service.obtainById(idUser);

        if (usuarioOptional.isPresent()){
            Usuario user = usuarioOptional.get();
            UsuarioDTO dto = new UsuarioDTO(user.getId(), user.getNome(), user.getEmail(), user.getRole(), user.getAtivo(), user.getTipo());
            return ResponseEntity.ok(dto);
        }

        return ResponseEntity.notFound().build();
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
    public ResponseEntity<UsuarioDTO> updateUser(@PathVariable String id, @RequestBody UsuarioDTO dto) {
        try {
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
            user.setTipo(dto.tipo());

            service.alterarUsuario(user);

            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            throw new RuntimeException(e.getMessage()); // colocar mensagem customizada de erro depois
        }
    }
}
