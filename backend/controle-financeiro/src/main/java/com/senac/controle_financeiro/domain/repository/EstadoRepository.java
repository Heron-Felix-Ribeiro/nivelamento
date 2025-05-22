package com.senac.controle_financeiro.domain.repository;

import com.senac.controle_financeiro.domain.entities.Estado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstadoRepository extends JpaRepository<Estado, Long> {
}
