package com.senac.controle_financeiro.application.services.interfaces;

import com.senac.controle_financeiro.application.object.empresa.EmpresaRequest;
import com.senac.controle_financeiro.application.object.empresa.EmpresaResponse;

import java.util.List;

public interface IEmpresaService {

    EmpresaResponse salvar(EmpresaRequest entrada);

    List<EmpresaResponse> listarTodosAtivos();

    List<EmpresaResponse> listarTodosInativos();

    Integer contarTodasAtivas();

    EmpresaResponse listarPorId(Long id);

    EmpresaResponse editar(Long id, EmpresaRequest entrada);

    Void mudarStatus(Long id);
}
