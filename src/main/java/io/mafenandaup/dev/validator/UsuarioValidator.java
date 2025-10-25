package io.mafenandaup.dev.validator;

import io.mafenandaup.dev.model.Usuario;
import io.mafenandaup.dev.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UsuarioValidator {

    private UsuarioRepository repository;

    public UsuarioValidator(UsuarioRepository repository) {
        this.repository = repository;
    }

    public void validarRegistro(Usuario user) throws Exception {
        if (userExists(user)){
            throw new Exception("O Usuário registrado já existe. tente novamente.");
        }
    }

    private boolean userExists(Usuario user){
        Optional<Usuario> userFound = repository.findByNomeAndEmail(
               user.getNome(),
                user.getEmail()
        );
        if (user.getId() ==null){
            return userFound.isPresent();
        }
        return !user.getId().equals(userFound.get()) && userFound.isPresent();
    }
}