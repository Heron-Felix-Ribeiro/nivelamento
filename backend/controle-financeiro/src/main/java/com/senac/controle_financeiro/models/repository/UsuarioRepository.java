package com.senac.controle_financeiro.models.repository;

import com.senac.controle_financeiro.models.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {



}
