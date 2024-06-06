package br.com.fiap.reciclaki.repositories;

import br.com.fiap.reciclaki.domain.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
}
