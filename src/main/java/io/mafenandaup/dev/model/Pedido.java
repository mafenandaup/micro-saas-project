package io.mafenandaup.dev.model;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.UUID;


import java.math.BigDecimal;
import java.time.LocalDateTime;


@Entity
@Getter
@Setter
@Table(name = "pedido")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_pedido")
    private UUID id;

    @Column(name = "code_pedido")
   private Integer codigo;

    @ManyToOne
    @JoinColumn(name = "cliente_fk")
    private Usuario cliente;

    @ManyToOne
    @JoinColumn(name = "representante_fk")
    private Usuario representante;

    @Column(name = "status_pedido")
    @Enumerated(EnumType.STRING)
    private StatusPedido statusPedido;

    @Column(name = "status_pre_pedido")
    @Enumerated(EnumType.STRING)
    private StatusPrePedido statusPrePedido; //não é obrigatório, so se o outro enum estiver como PROCESSANDO

    @Column(name = "valor_total")
    private BigDecimal valorTotal;

    @Column(name = "criado_em")
    private LocalDateTime createdAt;

    @Column(name = "atualizado_em")
    private LocalDateTime updatedAt;

    @Column(name = "lote")
    private Integer lote;



}
