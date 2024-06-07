package br.com.fiap.reciclaki.repositories;

import br.com.fiap.reciclaki.domain.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    @Query("from Usuario u where u.nome like %:nome%")
    Page<Usuario> buscarPorNome(@Param("nome") String nome, Pageable page);

}
