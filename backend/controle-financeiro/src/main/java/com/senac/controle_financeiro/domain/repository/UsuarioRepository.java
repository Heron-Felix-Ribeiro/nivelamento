package com.senac.controle_financeiro.domain.repository;

import com.senac.controle_financeiro.domain.entities.Usuario;
import com.senac.controle_financeiro.domain.valueObjects.Email;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByEmail (Email email);
    Boolean existsByEmail (Email email);
    List<Usuario> findByEstaAtivoTrueAndEmpresaIdNotNull();
    List<Usuario> findByEstaAtivoFalseAndEmpresaIdNotNull();
    Integer countByEstaAtivoTrue();
    List<Usuario> findByEmpresaId(Long id);
}
