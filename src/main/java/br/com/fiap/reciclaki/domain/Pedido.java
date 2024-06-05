package br.com.fiap.reciclaki.domain;

import br.com.fiap.reciclaki.domain.enums.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@Entity
@Table(name="gs_pedido")
@SequenceGenerator(name="seq_pedido", sequenceName="seq_gs_pedido", allocationSize=1)
public class Pedido {

    @Id
    @Column(name="cd_pedido")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq_pedido")
    private Long id;

    @Column(name ="qtd_itens")
    private int quantidade;

    @Column(name="dt_pedido", nullable=false)
    private LocalDate dataPedido;

    @Column(name = "sts_pedido",nullable = false, length = 20)
    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne
    @JoinColumn(name ="cd_produto", nullable = false)
    private Produto produto;

    @OneToOne
    @JoinColumn(name="cd_endereco", nullable = false)
    private Endereco endereco;

    @ManyToOne
    @JoinColumn(name ="cd_cooperativa", nullable = false)
    private Cooperativa cooperativa;

}
