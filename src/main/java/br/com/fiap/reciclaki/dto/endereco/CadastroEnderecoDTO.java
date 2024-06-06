package br.com.fiap.reciclaki.dto.endereco;

public record CadastroEnderecoDTO(
        String logradouro,
        String cep,
        Long cidadeId
) {
}
