package br.com.fiap.reciclaki.controller;

import br.com.fiap.reciclaki.domain.Endereco;
import br.com.fiap.reciclaki.dto.endereco.CadastroEnderecoDTO;
import br.com.fiap.reciclaki.dto.endereco.DetalhesEnderecoDTO;
import br.com.fiap.reciclaki.repositories.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/enderecos")
public class EnderecoController {

    @Autowired
    private EnderecoRepository enderecoRepository;

    @PostMapping
    public ResponseEntity<DetalhesEnderecoDTO> cadastrar(@RequestBody CadastroEnderecoDTO dto, UriComponentsBuilder uriBuilder) {
        var endereco = new Endereco(dto);
        enderecoRepository.save(endereco);
        var uri = uriBuilder.path("/enderecos/{id}").buildAndExpand(endereco.getId()).toUri();
        return ResponseEntity.created(uri).body(new DetalhesEnderecoDTO(endereco));
    }

    @GetMapping
    public ResponseEntity<Page<DetalhesEnderecoDTO>> pesquisar(Pageable pageable) {
        var page = enderecoRepository.findAll(pageable).map(DetalhesEnderecoDTO::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalhesEnderecoDTO> pesquisar(@PathVariable("id") Long id) {
        var endereco = new DetalhesEnderecoDTO(enderecoRepository.getReferenceById(id));
        return ResponseEntity.ok(endereco);
    }


}
