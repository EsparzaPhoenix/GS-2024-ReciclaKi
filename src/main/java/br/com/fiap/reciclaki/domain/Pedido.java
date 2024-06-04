package br.com.fiap.reciclaki.domain;

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

    @Column(name="dt_pedido", nullable=false)
    private LocalDate dataPedido;

    //@ManyToOne

}
