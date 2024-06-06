package br.com.fiap.reciclaki.dto.endereco;

import br.com.fiap.reciclaki.domain.Endereco;
import br.com.fiap.reciclaki.dto.cidade.DetalhesCidadeDTO;

public record DetalhesEnderecoDTO(
        Long id,
        String logradouro,
        String cep,
        DetalhesCidadeDTO cidade) {
    public DetalhesEnderecoDTO(Endereco endereco) {
        this(
                endereco.getId(),
                endereco.getLogradouro(),
                endereco.getCep(),
                new DetalhesCidadeDTO(endereco.getCidade())
        );
    }
}
