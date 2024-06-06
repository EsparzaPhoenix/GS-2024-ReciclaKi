package br.com.fiap.reciclaki.dto.cooperativa;

import br.com.fiap.reciclaki.domain.Cooperativa;
import br.com.fiap.reciclaki.dto.endereco.DetalhesEnderecoDTO;

public record DetalhesCooperativaDTO(
        Long id,
        String nome,
        String descricao,
        DetalhesEnderecoDTO endereco){
    public DetalhesCooperativaDTO(Cooperativa cooperativa) {
        this(
                cooperativa.getId(),
                cooperativa.getNome(),
                cooperativa.getDescricao(),
                new DetalhesEnderecoDTO(cooperativa.getEndereco())
        );
    }
}
