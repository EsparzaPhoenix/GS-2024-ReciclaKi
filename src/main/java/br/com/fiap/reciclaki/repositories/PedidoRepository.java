package br.com.fiap.reciclaki.repositories;

import br.com.fiap.reciclaki.domain.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}
