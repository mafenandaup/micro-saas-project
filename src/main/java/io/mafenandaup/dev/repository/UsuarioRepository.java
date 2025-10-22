package io.mafenandaup.dev.repository;

import io.mafenandaup.dev.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UsuarioRepository extends JpaRepository <Usuario, UUID> {
}
