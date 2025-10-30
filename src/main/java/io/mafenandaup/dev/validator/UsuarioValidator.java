package io.mafenandaup.dev.validator;

import io.mafenandaup.dev.exceptions.DuplicateRegistryException;
import io.mafenandaup.dev.exceptions.InvalidRoleException;
import io.mafenandaup.dev.model.Role;
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

    public void validarRegistro(Usuario user) {
        if (userExists(user)){
            throw new DuplicateRegistryException("O usuário com este nome e e-mail já existe.");
        }
    }

    public void validateRole(Role role) {
        if (role == null ||
                (!role.equals(Role.ADMIN) &&
                        !role.equals(Role.CLIENTE) &&
                        !role.equals(Role.REPRESENTANTE))) {
            throw new InvalidRoleException("O valor referente ao cargo do usuário é inválido. Tente novamente.");
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
        return  userFound.isPresent() && !userFound.get().getId().equals(user.getId());
    }
}