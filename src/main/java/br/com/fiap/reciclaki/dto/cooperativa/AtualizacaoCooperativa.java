package br.com.fiap.reciclaki.dto.cooperativa;

import br.com.fiap.reciclaki.dto.endereco.CadastroEnderecoDTO;

public record AtualizacaoCooperativa(
        String nome,
        CadastroEnderecoDTO endereco
) {
}
