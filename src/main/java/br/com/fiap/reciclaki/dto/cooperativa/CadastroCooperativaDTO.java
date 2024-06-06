package br.com.fiap.reciclaki.dto.cooperativa;

import br.com.fiap.reciclaki.dto.endereco.CadastroEnderecoDTO;

public record CadastroCooperativaDTO(
        String nome,
        String descricao,
        CadastroEnderecoDTO endereco
){
}
