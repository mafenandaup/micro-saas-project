package io.mafenandaup.dev.dto;

import io.mafenandaup.dev.model.Role;
import io.mafenandaup.dev.model.TipoEmpresa;
import io.mafenandaup.dev.model.Usuario;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record UsuarioDTO(
        java.util.UUID id,
        @NotBlank(message = "Campo incompleto. tente novamente")
        String nome,

        @NotBlank(message = "Campo incompleto. tente novamente")
        String email,

        @NotBlank(message = "Campo incompleto. tente novamente")
        String senha,

        @Enumerated(EnumType.STRING)
        @NotNull(message = "Campo incompleto. tente novamente")
        Role role,

        @NotNull(message = "Campo incompleto. tente novamente")
        Boolean ativo


){
public Usuario mapAttributesUser(){
    Usuario user = new Usuario();
    user.setNome(this.nome);
    user.setEmail(this.email);
    user.setSenha(this.senha);
    user.setRole(this.role);
    user.setAtivo(this.ativo);
    return user;
}
}
