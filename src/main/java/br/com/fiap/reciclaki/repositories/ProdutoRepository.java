package br.com.fiap.reciclaki.repositories;

import br.com.fiap.reciclaki.domain.Produto;
import br.com.fiap.reciclaki.domain.enums.TipoMaterial;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    @Query("FROM Produto p WHERE p.tipoMaterial = :tipoMaterial")
    Page<Produto> buscarPorMaterial(@Param("tipoMaterial") TipoMaterial tipoMaterial, Pageable pageable);
}
