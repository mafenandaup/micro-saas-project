package io.mafenandaup.dev.model;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.UUID;

import java.math.BigDecimal;


@Entity
@Getter
@Setter
@Table(name = "produto")
public class Produto {


    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_produto")
    private UUID id;

    @Column(name = "nome")
    private String nome;


    @Column(name = "valor_unitario")
    private BigDecimal valorUnitario;

    @Column(name = "estoque")
    private Integer estoque;

    @Column(name = "categoria")
    private CategoriaProduto categoriaProduto;

    @Column(name = "disponivel")
    private Boolean disponivel;

}
