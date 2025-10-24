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

}
