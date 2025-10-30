package io.mafenandaup.dev.service;

import io.mafenandaup.dev.exceptions.InvalidArgsException;
import io.mafenandaup.dev.model.Usuario;
import io.mafenandaup.dev.repository.UsuarioRepository;
import io.mafenandaup.dev.validator.UsuarioValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UsuarioService {

    private UsuarioRepository repository;
    private UsuarioValidator validator;

    public UsuarioService(UsuarioRepository repository, UsuarioValidator validator) {
        this.repository = repository;
        this.validator = validator;
    }

    public List<Usuario> getUsers(){
        return repository.findAll();
    }

    public Usuario saveUser(Usuario usuario){
        validator.validarRegistro(usuario);
        validator.validateRole(usuario.getRole());
        return repository.save(usuario);
    }

    public Optional<Usuario> obtainById(UUID id) {
        var user = repository.findById(id);
        if (user.isEmpty()) {
            throw new InvalidArgsException("Usuário não encontrado para o ID informado.");
        }
        return user;
    }

    public void deleteUser(Usuario user) {
         repository.delete(user);
    }

    public void alterarUsuario( Usuario user){
        if (user.getId() == null){
            throw new InvalidArgsException("Usuário não encontrado/registrado. Tente novamente");
        }
        validator.validarRegistro(user);
        validator.validateRole(user.getRole());
        repository.save(user);
    }

}
