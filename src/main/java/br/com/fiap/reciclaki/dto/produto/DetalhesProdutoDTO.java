package br.com.fiap.reciclaki.dto.produto;

import br.com.fiap.reciclaki.domain.Produto;
import br.com.fiap.reciclaki.domain.enums.TipoMaterial;

public record DetalhesProdutoDTO(
        Long id,
        String nome,
        TipoMaterial tipoMaterial
) {
    public DetalhesProdutoDTO(Produto produto) {
        this(
                produto.getId(),
                produto.getNome(),
                produto.getTipoMaterial()
        );
    }
}
