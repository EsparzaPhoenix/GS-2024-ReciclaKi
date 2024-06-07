package br.com.fiap.reciclaki.repositories;

import br.com.fiap.reciclaki.domain.Pedido;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    // Consulta para buscar produtos por pesoPedido
    @Query("FROM Pedido p WHERE p.pesoPedido = :pesoPedido")
    Page<Pedido> buscarPorPesoPedido(@Param("pesoPedido") Float pesoPedido, Pageable pageable);

    // Consulta para buscar produtos por quantidade
    @Query("FROM Pedido p WHERE p.quantidade = :quantidade")
    Page<Pedido> buscarPorQuantidade(@Param("quantidade") Integer quantidade, Pageable pageable);

    // Consulta para buscar produtos por data
    @Query("from Pedido p where p.dataPedido between :inicio and :fim")
    Page<Pedido> buscarPorData(LocalDate inicio, LocalDate fim, Pageable pageable);

}
