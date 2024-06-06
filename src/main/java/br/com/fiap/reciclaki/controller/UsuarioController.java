package br.com.fiap.reciclaki.controller;

import br.com.fiap.reciclaki.domain.Endereco;
import br.com.fiap.reciclaki.domain.Usuario;
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
        // Buscar a cidade no banco de dados
        var cidade = cidadeRepository.getReferenceById(dto.endereco().cidadeId());
        // Criar o Endereço e associar a cidade
        var endereco = new Endereco(dto.endereco());
        endereco.setCidade(cidade);
        // Salvar o Endereço antes de associá-lo ao Usuario
        enderecoRepository.save(endereco);
        // Associar o Endereço ao Usuario
        usuario.setEndereco(endereco);
        // Salvar o Usuario
        usuario = usuarioRepository.save(usuario);
        var uri = builder.path("/usuarios/{id}").buildAndExpand(usuario.getId()).toUri();
        return ResponseEntity.created(uri).body(new DetalhesUsuarioDTO(usuario));
    }

    @GetMapping
    public ResponseEntity<Page<DetalhesUsuarioDTO>> pesquisar(Pageable pageable){
        var page = usuarioRepository.findAll(pageable).map(DetalhesUsuarioDTO::new);
        return ResponseEntity.ok(page);
    }

}
