package com.senac.controle_financeiro.application.services;

import com.senac.controle_financeiro.application.object.despesa.DespesaRequest;
import com.senac.controle_financeiro.application.object.despesa.DespesaResponse;
import com.senac.controle_financeiro.application.services.interfaces.IDespesaService;
import com.senac.controle_financeiro.domain.entities.TipoDespesa;
import com.senac.controle_financeiro.domain.repository.EmpresaRepository;
import com.senac.controle_financeiro.domain.repository.TipoDespesaRepository;
import com.senac.controle_financeiro.domain.repository.UsuarioRepository;
import com.senac.controle_financeiro.domain.valueObjects.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipoDespesaService implements IDespesaService {

    @Autowired
    private TipoDespesaRepository tipoDespesaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private EmpresaRepository empresaRepository;

    @Autowired
    private AuthService authService;

    @Override
    public DespesaResponse salvar(DespesaRequest entrada) {
        var usuarioLogado = usuarioRepository.findByEmail(new Email(entrada.usuario()))
                .orElseThrow(() -> new RuntimeException("Erro ao encontrar o usuario"));

        var empresa = empresaRepository.findByCnpj(entrada.cnpj())
                .orElseThrow(() -> new RuntimeException("Empresa não encontrada"));

        var salvo = tipoDespesaRepository.save( new TipoDespesa(entrada, usuarioLogado, empresa));

        return new DespesaResponse(
                salvo.getId(),
                salvo.getDespesa()
        );

    }

    @Override
    public DespesaResponse listarPorId(Long id) {
        var tipoDespesa = tipoDespesaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Erro ao encontrar o tipo de despesa"));

        return new DespesaResponse(
                tipoDespesa.getId(),
                tipoDespesa.getDespesa()
        );
    }

    @Override
    public List<DespesaResponse> listarTodos(String cnpj) {
        var empresa = empresaRepository.findByCnpj(cnpj)
                .orElseThrow(() -> new RuntimeException("Empresa não encontrada"));
        var despesas = tipoDespesaRepository.findByEmpresaId(empresa.getId());

        return despesas.stream()
                .map(despesa -> new DespesaResponse(
                        despesa.getId(),
                        despesa.getDespesa()))
                .toList();
    }

    @Override
    public DespesaResponse despesaEditada(DespesaRequest entrada) {
        var despesa = tipoDespesaRepository.findById(entrada.id());

        if (despesa.isPresent()) {
            var tipoDespesa = despesa.get();
            tipoDespesa.setId(entrada.id());
            tipoDespesa.setDespesa(entrada.despesa());
            tipoDespesaRepository.save(tipoDespesa);

            return new DespesaResponse(
                    tipoDespesa.getId(),
                    tipoDespesa.getDespesa());

        }
        throw new RuntimeException("Erro ao encontrar o tipo de despesa");
    }

    @Override
    public Long deletar(Long id) {
        var despesa = tipoDespesaRepository.findById(id);

        if (despesa.isPresent()) {
            tipoDespesaRepository.deleteById(id);
            return id;
        }
        throw new RuntimeException("Erro ao encontrar o tipo de despesa");
    }
}
