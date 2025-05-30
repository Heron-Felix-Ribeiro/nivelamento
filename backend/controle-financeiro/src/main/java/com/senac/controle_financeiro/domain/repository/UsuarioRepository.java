package com.senac.controle_financeiro.domain.repository;

import com.senac.controle_financeiro.domain.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByUsuarioIgnoreCase (String usuario);
    Optional<Usuario> findBySenha (String senha);

}
