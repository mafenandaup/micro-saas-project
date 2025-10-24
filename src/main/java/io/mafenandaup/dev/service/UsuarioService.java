package io.mafenandaup.dev.service;

import io.mafenandaup.dev.model.Usuario;
import io.mafenandaup.dev.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    public List<Usuario> getUsers(){
        return repository.findAll();
    }

    public Usuario saveUser(Usuario usuario){
        return repository.save(usuario);
    }

    public Optional<Usuario> obtainById(UUID id) {
        return repository.findById(id);
    }

    public void deleteUser(Usuario user) {
         repository.delete(user);
    }

    public void alterarUsuario( Usuario user){
        if (user.getId() == null){
            throw new IllegalArgumentException("Usuário não encontrado/registrado. Tente novamente");
        }
        // adicionar validações de campo com o validator aqui depois, além de verificação de registro nos outros métodos,etc
        repository.save(user);
    }

}
