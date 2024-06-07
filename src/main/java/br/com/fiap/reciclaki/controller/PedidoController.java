package br.com.fiap.reciclaki.controller;

import br.com.fiap.reciclaki.domain.Pedido;
import br.com.fiap.reciclaki.dto.pedido.AtualizacaoPedidoDTO;
import br.com.fiap.reciclaki.dto.pedido.CadastroPedidoDTO;
import br.com.fiap.reciclaki.dto.pedido.DetalhesPedidoDTO;
import br.com.fiap.reciclaki.dto.usuario.DetalhesUsuarioDTO;
import br.com.fiap.reciclaki.repositories.CooperativaRepository;
import br.com.fiap.reciclaki.repositories.EnderecoRepository;
import br.com.fiap.reciclaki.repositories.PedidoRepository;
import br.com.fiap.reciclaki.repositories.ProdutoRepository;
import jakarta.persistence.EntityNotFoundException;
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
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private CooperativaRepository cooperativaRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<DetalhesPedidoDTO> cadastrar(@RequestBody @Valid CadastroPedidoDTO dto, UriComponentsBuilder builder) {
        // Verificação e Logs
        System.out.println("DTO recebido: " + dto);

        var produto = produtoRepository.findById(dto.produtoId())
                .orElseThrow(() -> new EntityNotFoundException("Produto não encontrado com ID: " + dto.produtoId()));
        System.out.println("Produto encontrado: " + produto);

        var endereco = enderecoRepository.findById(dto.enderecoId())
                .orElseThrow(() -> new EntityNotFoundException("Endereço não encontrado com ID: " + dto.enderecoId()));
        System.out.println("Endereço encontrado: " + endereco);

        var cooperativa = cooperativaRepository.findById(dto.cooperativaId())
                .orElseThrow(() -> new EntityNotFoundException("Cooperativa não encontrada com ID: " + dto.cooperativaId()));
        System.out.println("Cooperativa encontrada: " + cooperativa);

        var pedido = new Pedido(dto);
        pedido.setProduto(produto);
        pedido.setEndereco(endereco);
        pedido.setCooperativa(cooperativa);

        if (pedido.getPesoPedido() <= 0) {
            throw new IllegalArgumentException("O peso do pedido deve ser maior que zero.");
        }

        System.out.println("Pedido a ser salvo: " + pedido);

        pedido = pedidoRepository.save(pedido);

        var uri = builder.path("/pedidos/{id}").buildAndExpand(pedido.getId()).toUri();
        return ResponseEntity.created(uri).body(new DetalhesPedidoDTO(pedido));

    }

    @GetMapping
    @Transactional
    public ResponseEntity<Page<DetalhesPedidoDTO>> pesquisar(Pageable pageable) {
        var page = pedidoRepository.findAll(pageable).map(DetalhesPedidoDTO::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalhesPedidoDTO> pesquisar(@PathVariable("id") Long id) {
        var pedido = new DetalhesPedidoDTO(pedidoRepository.getReferenceById(id));
        return ResponseEntity.ok(pedido);
    }

    @GetMapping("por-peso")
    public ResponseEntity<Page<DetalhesPedidoDTO>> buscarPorPeso(@RequestParam("pesoPedido")Float pesoPedido, Pageable pageable){
        var lista = pedidoRepository.buscarPorPesoPedido(pesoPedido, pageable).map(DetalhesPedidoDTO::new);
        return ResponseEntity.ok(lista);
    }

    @GetMapping("por-quantidade")
    public ResponseEntity<Page<DetalhesPedidoDTO>> buscarPorQuantidade(@RequestParam("quantidade")Integer quantidade, Pageable pageable){
        var lista = pedidoRepository.buscarPorQuantidade(quantidade, pageable).map(DetalhesPedidoDTO::new);
        return ResponseEntity.ok(lista);
    }

    @GetMapping("por-data")
    public ResponseEntity<Page<DetalhesPedidoDTO>> buscarPorData(@RequestParam("data-inicio")LocalDate inicio, @RequestParam("data-fim") LocalDate fim, Pageable pageable) {
        var lista = pedidoRepository.buscarPorData(inicio,fim,pageable).map(DetalhesPedidoDTO::new);
        return ResponseEntity.ok(lista);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DetalhesPedidoDTO> atualizar(@PathVariable("id") Long id, @RequestBody @Valid AtualizacaoPedidoDTO dto) {
        var pedido = pedidoRepository.getReferenceById(id);
        pedido.atualizar(dto);
        return ResponseEntity.ok(new DetalhesPedidoDTO(pedido));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> remover(@PathVariable("id") Long id){
        pedidoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
