package br.com.fiap.reciclaki.controller;

import br.com.fiap.reciclaki.domain.Cidade;
import br.com.fiap.reciclaki.dto.cidade.AtualizacaoCidadeDTO;
import br.com.fiap.reciclaki.dto.cidade.CadastroCidadeDTO;
import br.com.fiap.reciclaki.dto.cidade.DetalhesCidadeDTO;
import br.com.fiap.reciclaki.repositories.CidadeRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/cidades")
public class CidadeController {

    @Autowired
    private CidadeRepository cidadeRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<DetalhesCidadeDTO> cadastrar(@RequestBody @Valid CadastroCidadeDTO dto, UriComponentsBuilder builder) {
        var cidade = new Cidade(dto);
        cidade = cidadeRepository.save(cidade);
        var uri = builder.path("/cidades/{id}").buildAndExpand(cidade.getId()).toUri();
        return ResponseEntity.created(uri).body(new DetalhesCidadeDTO(cidade));
    }

    @GetMapping
    public ResponseEntity<Page<DetalhesCidadeDTO>> pesquisar(Pageable pageable){
        var page = cidadeRepository.findAll(pageable).map(DetalhesCidadeDTO::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalhesCidadeDTO> pesquisar(@PathVariable("id") Long id) {
        var pedido = new DetalhesCidadeDTO(cidadeRepository.getReferenceById(id));
        return ResponseEntity.ok(pedido);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DetalhesCidadeDTO> atualizar(@PathVariable("id") Long id, @RequestBody @Valid AtualizacaoCidadeDTO dto) {
        var pedido = cidadeRepository.getReferenceById(id);
        pedido.atualizar(dto);
        return ResponseEntity.ok(new DetalhesCidadeDTO(pedido));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> remover(@PathVariable("id") Long id){
        cidadeRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
