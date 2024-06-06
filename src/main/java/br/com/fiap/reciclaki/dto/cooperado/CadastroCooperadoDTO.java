package br.com.fiap.reciclaki.dto.cooperado;

import br.com.fiap.reciclaki.dto.endereco.CadastroEnderecoDTO;

public record CadastroCooperadoDTO(
        String nome,
        CadastroEnderecoDTO endereco,
        Long cooperativaId
) {
}
