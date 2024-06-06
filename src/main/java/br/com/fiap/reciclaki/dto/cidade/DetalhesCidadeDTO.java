package br.com.fiap.reciclaki.dto.cidade;

import br.com.fiap.reciclaki.domain.Cidade;

public record DetalhesCidadeDTO(
        Long id,
        String nome,
        String uf,
        Integer ddd
) {
    public DetalhesCidadeDTO(Cidade post) {
        this(
                post.getId(),
                post.getNome(),
                post.getUf(),
                post.getDdd()
        );
    }
}
