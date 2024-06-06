package br.com.fiap.reciclaki.dto.pedido;

import br.com.fiap.reciclaki.domain.enums.Status;
import br.com.fiap.reciclaki.dto.endereco.CadastroEnderecoDTO;

import java.time.LocalDate;

public record AtualizacaoPedidoDTO(
        Integer quantidade,
        Float pesoPedido,
        LocalDate dataPedido,
        Status status,
        Long produtoId,
        CadastroEnderecoDTO endereco,
        Long cooperativaId
) {
}
