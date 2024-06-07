package br.com.fiap.reciclaki.controller;

import br.com.fiap.reciclaki.domain.Cooperado;
import br.com.fiap.reciclaki.domain.Endereco;
import br.com.fiap.reciclaki.dto.cooperado.AtualizacaoCooperadoDTO;
import br.com.fiap.reciclaki.dto.cooperado.CadastroCooperadoDTO;
import br.com.fiap.reciclaki.dto.cooperado.DetalhesCooperadoDTO;
import br.com.fiap.reciclaki.dto.endereco.DetalhesEnderecoDTO;
import br.com.fiap.reciclaki.repositories.CidadeRepository;
import br.com.fiap.reciclaki.repositories.CooperadoRepository;
import br.com.fiap.reciclaki.repositories.CooperativaRepository;
import br.com.fiap.reciclaki.repositories.EnderecoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/cooperados")
public class CooperadoController {

    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private CooperativaRepository cooperativaRepository;

    @Autowired
    private CooperadoRepository cooperadoRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<DetalhesCooperadoDTO> cadastrar(@RequestBody @Valid CadastroCooperadoDTO dto, UriComponentsBuilder builder) {
        var cooperado = new Cooperado(dto);
        var cidade = cidadeRepository.getReferenceById(dto.endereco().cidadeId());
        var endereco = new Endereco(dto.endereco());
        endereco.setCidade(cidade);
        enderecoRepository.save(endereco);
        cooperado.setEndereco(endereco);
        var cooperativa = cooperativaRepository.getReferenceById(dto.cooperativaId());
        cooperado.setCooperativa(cooperativa);
        cooperado = cooperadoRepository.save(cooperado);
        var uri = builder.path("/cooperados/{id}").buildAndExpand(cooperado.getId()).toUri();
        return ResponseEntity.created(uri).body(new DetalhesCooperadoDTO(cooperado));
    }

    @GetMapping
    public ResponseEntity<Page<DetalhesCooperadoDTO>> pesquisar(Pageable pageable){
        var page = cooperadoRepository.findAll(pageable).map(DetalhesCooperadoDTO::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalhesCooperadoDTO> pesquisar(@PathVariable("id") Long id) {
        var endereco = new DetalhesCooperadoDTO(cooperadoRepository.getReferenceById(id));
        return ResponseEntity.ok(endereco);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DetalhesCooperadoDTO> atualizar(@PathVariable Long id, @RequestBody @Valid AtualizacaoCooperadoDTO dto) {
        var cooperado = cooperadoRepository.getReferenceById(id);
        cooperado.atualizar(dto);
        return ResponseEntity.ok(new DetalhesCooperadoDTO(cooperado));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        cooperadoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
