package br.com.fiap.reciclaki.dto.endereco;

public record AtualizacaoEnderecoDTO(
        String logradouro,
        String cep,
        Long cidadeId
) {
}