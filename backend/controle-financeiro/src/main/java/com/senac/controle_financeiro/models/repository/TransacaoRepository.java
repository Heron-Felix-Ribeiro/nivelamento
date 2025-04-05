package com.senac.controle_financeiro.models.repository;

import com.senac.controle_financeiro.models.entities.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TransacaoRepository extends JpaRepository<Transacao, Long> {

    Optional<Transacao> findByUsuarioId (Long usuarioId);
}
