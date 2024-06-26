package br.com.fiap.reciclaki.dto.pedido;

import br.com.fiap.reciclaki.domain.enums.Status;
import br.com.fiap.reciclaki.dto.endereco.CadastroEnderecoDTO;

import java.time.LocalDate;

public record CadastroPedidoDTO(
        Integer quantidade,
        Float pesoPedido,
        LocalDate dataPedido,
        Status status,
        Long produtoId,
        Long enderecoId,
        Long cooperativaId
) {
}
