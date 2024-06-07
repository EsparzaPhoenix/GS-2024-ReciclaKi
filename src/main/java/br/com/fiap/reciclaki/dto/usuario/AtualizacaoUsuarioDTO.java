package br.com.fiap.reciclaki.dto.usuario;

import br.com.fiap.reciclaki.dto.endereco.CadastroEnderecoDTO;

public record AtualizacaoUsuarioDTO(
        String email,
        String numeroTelefone,
        String nome,
        CadastroEnderecoDTO endereco
) {
}
