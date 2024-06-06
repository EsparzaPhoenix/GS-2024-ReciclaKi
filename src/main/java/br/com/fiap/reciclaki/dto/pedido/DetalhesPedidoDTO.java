package br.com.fiap.reciclaki.dto.pedido;

import br.com.fiap.reciclaki.domain.Pedido;
import br.com.fiap.reciclaki.dto.endereco.DetalhesEnderecoDTO;

import java.time.LocalDate;

public record DetalhesPedidoDTO(
        Long id,
        double pesoPedido,
        LocalDate dataPedido,
        Long produtoId,
        DetalhesEnderecoDTO endereco,
        Long cooperativaId
) {
    public DetalhesPedidoDTO(Pedido pedido) {
        this(
                pedido.getId(),
                pedido.getPesoPedido(),
                pedido.getDataPedido(),
                pedido.getProduto().getId(),
                new DetalhesEnderecoDTO(pedido.getEndereco()),
                pedido.getCooperativa().getId()
        );
    }
}
