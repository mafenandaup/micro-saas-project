package io.mafenandaup.dev.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.UUID;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@Table(name = "item_pedido")
public class ItemPedido {


    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_item_pedido")
    private UUID id;

    @ManyToOne
    private Pedido pedido;

    @ManyToOne
    private Produto produto;

    @Column(name = "valor_unitario")
    private BigDecimal valorUnitario;

    @Column(name = "quantidade")
    private Integer quantidade;

    @Column(name = "subtotal")
    private BigDecimal subtotal; //(QUANTIDADE * P.Unit)

}
