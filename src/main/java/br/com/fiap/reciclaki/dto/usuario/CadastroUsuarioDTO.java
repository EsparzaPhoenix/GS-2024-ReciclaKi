package br.com.fiap.reciclaki.dto.usuario;

import br.com.fiap.reciclaki.dto.endereco.CadastroEnderecoDTO;

public record CadastroUsuarioDTO(
        String nome,
        String email,
        String numeroTelefone,
        CadastroEnderecoDTO endereco
) {
}
