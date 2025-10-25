package io.mafenandaup.dev.dto;

import io.mafenandaup.dev.model.Role;
import io.mafenandaup.dev.model.TipoEmpresa;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

public record UsuarioDTO(
        java.util.UUID id,
        @NotBlank(message = "Campo incompleto. tente novamente")
        String nome,

        @NotBlank(message = "Campo incompleto. tente novamente")
        String email,

        @Enumerated(EnumType.STRING)
        Role role,

        @NotEmpty(message = "Campo incompleto. tente novamente")
        Boolean ativo,

        @Enumerated(EnumType.STRING)
        TipoEmpresa tipo

) {



}
