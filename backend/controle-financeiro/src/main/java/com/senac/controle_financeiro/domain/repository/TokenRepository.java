package com.senac.controle_financeiro.domain.repository;

import com.senac.controle_financeiro.domain.entities.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepository extends JpaRepository<Token, Long> {
}
