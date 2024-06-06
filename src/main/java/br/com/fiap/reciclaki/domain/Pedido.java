package br.com.fiap.reciclaki.domain;

import br.com.fiap.reciclaki.domain.enums.Status;
import br.com.fiap.reciclaki.dto.pedido.AtualizacaoPedidoDTO;
import br.com.fiap.reciclaki.dto.pedido.CadastroPedidoDTO;
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
    private Integer quantidade;

    @Column(name ="peso_pedido")
    private Float pesoPedido;
    //peso total em kilos dos produtos a serem reciclados

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

    public Pedido(CadastroPedidoDTO dto) {
        quantidade = dto.quantidade();
        pesoPedido = dto.pesoPedido();
        dataPedido = dto.dataPedido();
        status = dto.status();
    }

    public void atualizar(AtualizacaoPedidoDTO dto) {
        if(dto.quantidade() != null)
            quantidade = dto.quantidade();
        if(dto.pesoPedido() != null)
            pesoPedido = dto.pesoPedido();
        if(dto.dataPedido() != null)
            dataPedido = dto.dataPedido();
        if(dto.status() != null)
            status = dto.status();
    }
}
