package br.com.fiap.reciclaki.repositories;

import br.com.fiap.reciclaki.domain.Cidade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CidadeRepository extends JpaRepository<Cidade, Long> {
}
