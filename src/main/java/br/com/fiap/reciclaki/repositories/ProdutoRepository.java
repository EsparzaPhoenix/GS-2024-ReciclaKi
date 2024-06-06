package br.com.fiap.reciclaki.repositories;

import br.com.fiap.reciclaki.domain.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
