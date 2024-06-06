package br.com.fiap.reciclaki.dto.pedido;

import br.com.fiap.reciclaki.domain.Pedido;
import br.com.fiap.reciclaki.domain.enums.Status;
import br.com.fiap.reciclaki.dto.endereco.DetalhesEnderecoDTO;

import java.time.LocalDate;

public record DetalhesPedidoDTO(
        Long id,
        Integer quantidade,
        double pesoPedido,
        LocalDate dataPedido,
        Status status,
        Long produtoId,
        DetalhesEnderecoDTO endereco,
        Long cooperativaId
) {
    public DetalhesPedidoDTO(Pedido pedido) {
        this(
                pedido.getId(),
                pedido.getQuantidade(),
                pedido.getPesoPedido(),
                pedido.getDataPedido(),
                pedido.getStatus(),
                pedido.getProduto().getId(),
                new DetalhesEnderecoDTO(pedido.getEndereco()),
                pedido.getCooperativa().getId()
        );
    }
}
