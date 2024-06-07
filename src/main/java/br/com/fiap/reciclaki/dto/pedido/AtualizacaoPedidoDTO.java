package br.com.fiap.reciclaki.dto.pedido;

import br.com.fiap.reciclaki.domain.enums.Status;

import java.time.LocalDate;

public record AtualizacaoPedidoDTO(
        Integer quantidade,
        Float pesoPedido,
        LocalDate dataPedido,
        Status status,
        Long produtoId,
        Long enderecoId,
        Long cooperativaId
) {
}
