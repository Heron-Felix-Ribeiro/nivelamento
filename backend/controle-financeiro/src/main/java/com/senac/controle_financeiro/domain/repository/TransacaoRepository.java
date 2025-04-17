package com.senac.controle_financeiro.domain.repository;

import com.senac.controle_financeiro.domain.entities.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransacaoRepository extends JpaRepository<Transacao, Long> {

    List<Transacao> findByUsuarioId (Long usuarioId);
}
