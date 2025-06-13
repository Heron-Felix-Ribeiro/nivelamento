package com.senac.controle_financeiro.domain.repository;

import com.senac.controle_financeiro.domain.entities.TipoDespesa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TipoDespesaRepository extends JpaRepository<TipoDespesa, Long> {

    List<TipoDespesa> findByUsuarioId(Long usuarioId);
    Optional<TipoDespesa> findByDespesa(String despesa);
    List<TipoDespesa> findByEmpresaId(Long empresaId);
}
