package br.com.fiap.reciclaki.dto.produto;

import br.com.fiap.reciclaki.domain.enums.TipoMaterial;

public record CadastroProdutoDTO(
        TipoMaterial tipoMaterial,
        String nome
) {
}
