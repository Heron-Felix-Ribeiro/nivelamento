package com.senac.controle_financeiro.application.services.interfaces;

import com.senac.controle_financeiro.application.object.despesa.DespesaRequest;
import com.senac.controle_financeiro.application.object.despesa.DespesaResponse;
import com.senac.controle_financeiro.domain.entities.TipoDespesa;

import java.util.List;

public interface IDespesaService {

    DespesaResponse salvar(DespesaRequest entrada);

    DespesaResponse listarPorId(Long id);

    List<DespesaResponse> listarTodos(String cnpj);

    DespesaResponse despesaEditada(DespesaRequest entrada);

    Long deletar(Long id);
}
