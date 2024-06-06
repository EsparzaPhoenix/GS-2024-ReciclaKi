package br.com.fiap.reciclaki.repositories;

import br.com.fiap.reciclaki.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
