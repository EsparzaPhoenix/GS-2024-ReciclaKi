package br.com.fiap.reciclaki.controller;

import br.com.fiap.reciclaki.domain.Cooperativa;
import br.com.fiap.reciclaki.domain.Endereco;
import br.com.fiap.reciclaki.dto.cooperativa.AtualizacaoCooperativa;
import br.com.fiap.reciclaki.dto.cooperativa.CadastroCooperativaDTO;
import br.com.fiap.reciclaki.dto.cooperativa.DetalhesCooperativaDTO;
import br.com.fiap.reciclaki.repositories.CooperativaRepository;
import br.com.fiap.reciclaki.repositories.EnderecoRepository;
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
@RequestMapping("/cooperativas")
public class CooperativaController {

    @Autowired
    private CooperativaRepository cooperativaRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private CidadeRepository cidadeRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<DetalhesCooperativaDTO> cadastrar(@RequestBody @Valid CadastroCooperativaDTO dto, UriComponentsBuilder builder) {
        var cooperativa = new Cooperativa(dto);
        var cidade = cidadeRepository.getReferenceById(dto.endereco().cidadeId());
        var endereco = new Endereco(dto.endereco());
        endereco.setCidade(cidade);
        enderecoRepository.save(endereco);
        cooperativa.setEndereco(endereco);
        cooperativa = cooperativaRepository.save(cooperativa);
        var uri = builder.path("/cooperativas/{id}").buildAndExpand(cooperativa.getId()).toUri();
        return ResponseEntity.created(uri).body(new DetalhesCooperativaDTO(cooperativa));
    }

    @GetMapping
    public ResponseEntity<Page<DetalhesCooperativaDTO>> pesquisar(Pageable pageable){
        var page = cooperativaRepository.findAll(pageable).map(DetalhesCooperativaDTO::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DetalhesCooperativaDTO> atualizar(@PathVariable Long id, @RequestBody @Valid AtualizacaoCooperativa dto) {
        var cooperativa = cooperativaRepository.getReferenceById(id);
        cooperativa.atualizar(dto);
        return ResponseEntity.ok(new DetalhesCooperativaDTO(cooperativa));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        cooperativaRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
