package com.senac.controle_financeiro.domain.repository;

import com.senac.controle_financeiro.domain.entities.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmpresaRepository extends JpaRepository<Empresa, Long> {
    @Query("SELECT e FROM Empresa e WHERE e.cnpj.cnpj = :cnpj")
    Optional<Empresa> findByCnpj (@Param("cnpj") String cnpj);

    Integer countByEstaAtivoTrue();

    List<Empresa> findByEstaAtivo(Boolean ativo);
}
