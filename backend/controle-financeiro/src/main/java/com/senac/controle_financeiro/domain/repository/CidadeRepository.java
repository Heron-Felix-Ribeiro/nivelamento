package com.senac.controle_financeiro.domain.repository;

import com.senac.controle_financeiro.domain.entities.Cidade;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CidadeRepository extends JpaRepository<Cidade, Long> {
    Optional<Cidade> findByIbge(String ibge);
}
