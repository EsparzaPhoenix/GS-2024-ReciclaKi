package br.com.fiap.reciclaki.controller;

import br.com.fiap.reciclaki.domain.Produto;
import br.com.fiap.reciclaki.domain.enums.TipoMaterial;
import br.com.fiap.reciclaki.dto.pedido.DetalhesPedidoDTO;
import br.com.fiap.reciclaki.dto.produto.CadastroProdutoDTO;
import br.com.fiap.reciclaki.dto.produto.AtualizacaoProdutoDTO;
import br.com.fiap.reciclaki.dto.produto.DetalhesProdutoDTO;
import br.com.fiap.reciclaki.repositories.ProdutoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDate;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<DetalhesProdutoDTO> cadastrar(@RequestBody @Valid CadastroProdutoDTO dto, UriComponentsBuilder builder) {
        var produto = new Produto(dto);
        produto = produtoRepository.save(produto);
        var uri = builder.path("/produtos/{id}").buildAndExpand(produto.getId()).toUri();
        return ResponseEntity.created(uri).body(new DetalhesProdutoDTO(produto));
    }

    @GetMapping
    public ResponseEntity<Page<DetalhesProdutoDTO>> pesquisar(Pageable pageable){
        var page = produtoRepository.findAll(pageable).map(DetalhesProdutoDTO::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalhesProdutoDTO> detalhar(@PathVariable Long id) {
        var produto = produtoRepository.getReferenceById(id);
        return ResponseEntity.ok(new DetalhesProdutoDTO(produto));
    }

    @GetMapping("por-material")
    public ResponseEntity<Page<DetalhesProdutoDTO>> buscarPorMaterial(@RequestParam("TipoMaterial") TipoMaterial tipoMaterial, Pageable pageable){
        var lista = produtoRepository.buscarPorMaterial(tipoMaterial, pageable).map(DetalhesProdutoDTO::new);
        return ResponseEntity.ok(lista);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DetalhesProdutoDTO> atualizar(@PathVariable Long id, @RequestBody @Valid AtualizacaoProdutoDTO dto) {
        var produto = produtoRepository.getReferenceById(id);
        produto.atualizar(dto);
        return ResponseEntity.ok(new DetalhesProdutoDTO(produto));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        produtoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
