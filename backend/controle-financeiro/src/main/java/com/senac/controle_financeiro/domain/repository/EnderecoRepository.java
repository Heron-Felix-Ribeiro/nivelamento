package com.senac.controle_financeiro.domain.repository;

import com.senac.controle_financeiro.domain.entities.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
}
