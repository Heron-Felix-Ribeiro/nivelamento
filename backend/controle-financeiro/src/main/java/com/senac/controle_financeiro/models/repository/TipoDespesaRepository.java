package com.senac.controle_financeiro.models.repository;

import com.senac.controle_financeiro.models.entities.TipoDespesa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TipoDespesaRepository extends JpaRepository<TipoDespesa, Long> {

    List<TipoDespesa> findByUsuarioId(Long usuarioId);
}
