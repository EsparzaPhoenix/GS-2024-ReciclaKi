package br.com.fiap.reciclaki.dto.usuario;

import br.com.fiap.reciclaki.domain.Usuario;
import br.com.fiap.reciclaki.dto.endereco.DetalhesEnderecoDTO;

public record DetalhesUsuarioDTO(
        Long id,
        String email,
        String numeroTelefone,
        String nome,
        DetalhesEnderecoDTO endereco
) {
    public DetalhesUsuarioDTO(Usuario usuario) {
        this(
                usuario.getId(),
                usuario.getEmail(),
                usuario.getNumeroTelefone(),
                usuario.getNome(),
                new DetalhesEnderecoDTO(usuario.getEndereco())
        );
    }
}
