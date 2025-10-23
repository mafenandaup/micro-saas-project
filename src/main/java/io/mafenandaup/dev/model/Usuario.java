package io.mafenandaup.dev.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.UUID;


import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_usuario")
    private UUID id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "email")
    private String email;

    @Column(name = "senha")
    private String senha;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Role role;

    @Column(name = "ativo")
    private Boolean ativo;


    @Column(name = "criado_em")
    private LocalDateTime createdAt;


    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_empresa")
    private TipoEmpresa tipo;
}
