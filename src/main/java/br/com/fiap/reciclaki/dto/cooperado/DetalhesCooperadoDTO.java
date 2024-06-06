package br.com.fiap.reciclaki.dto.cooperado;

import br.com.fiap.reciclaki.domain.Cooperado;
import br.com.fiap.reciclaki.dto.cooperativa.DetalhesCooperativaDTO;
import br.com.fiap.reciclaki.dto.endereco.DetalhesEnderecoDTO;

public record DetalhesCooperadoDTO(
        Long id,
        String nome,
        DetalhesEnderecoDTO endereco,
        Long cooperativaId
) {
    public DetalhesCooperadoDTO(Cooperado cooperado) {
        this(
                cooperado.getId(),
                cooperado.getNome(),
                new DetalhesEnderecoDTO(cooperado.getEndereco()),
                cooperado.getCooperativa().getId()
        );
    }
}
