package br.com.fiap.reciclaki.controller;

import br.com.fiap.reciclaki.domain.Endereco;
import br.com.fiap.reciclaki.domain.Usuario;
import br.com.fiap.reciclaki.dto.produto.AtualizacaoProdutoDTO;
import br.com.fiap.reciclaki.dto.produto.DetalhesProdutoDTO;
import br.com.fiap.reciclaki.dto.usuario.AtualizacaoUsuarioDTO;
import br.com.fiap.reciclaki.dto.usuario.CadastroUsuarioDTO;
import br.com.fiap.reciclaki.dto.usuario.DetalhesUsuarioDTO;
import br.com.fiap.reciclaki.repositories.CidadeRepository;
import br.com.fiap.reciclaki.repositories.EnderecoRepository;
import br.com.fiap.reciclaki.repositories.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<DetalhesUsuarioDTO> cadastrar(@RequestBody @Valid CadastroUsuarioDTO dto, UriComponentsBuilder builder) {
        var usuario = new Usuario(dto);
        var cidade = cidadeRepository.getReferenceById(dto.endereco().cidadeId());
        var endereco = new Endereco(dto.endereco());
        endereco.setCidade(cidade);
        enderecoRepository.save(endereco);
        usuario.setEndereco(endereco);
        usuario = usuarioRepository.save(usuario);
        var uri = builder.path("/usuarios/{id}").buildAndExpand(usuario.getId()).toUri();
        return ResponseEntity.created(uri).body(new DetalhesUsuarioDTO(usuario));
    }

    @GetMapping
    public ResponseEntity<Page<DetalhesUsuarioDTO>> pesquisar(Pageable pageable){
        var page = usuarioRepository.findAll(pageable).map(DetalhesUsuarioDTO::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalhesUsuarioDTO> detalhar(@PathVariable Long id) {
        var usuario = usuarioRepository.getReferenceById(id);
        return ResponseEntity.ok(new DetalhesUsuarioDTO(usuario));
    }

    @GetMapping("por-nome")
    public ResponseEntity<Page<DetalhesUsuarioDTO>> buscar(@RequestParam("nome")String nome, Pageable pageable){
        var lista = usuarioRepository.buscarPorNome(nome, pageable).map(DetalhesUsuarioDTO::new);
        return ResponseEntity.ok(lista);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DetalhesUsuarioDTO> atualizar(@PathVariable Long id, @RequestBody @Valid AtualizacaoUsuarioDTO dto) {
        var usuario = usuarioRepository.getReferenceById(id);
        usuario.atualizar(dto);
        return ResponseEntity.ok(new DetalhesUsuarioDTO(usuario));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        usuarioRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
