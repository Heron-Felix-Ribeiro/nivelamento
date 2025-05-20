package com.senac.controle_financeiro.application.services;

import com.senac.controle_financeiro.application.object.despesa.DespesaRequest;
import com.senac.controle_financeiro.application.object.despesa.DespesaResponse;
import com.senac.controle_financeiro.application.services.interfaces.IDespesaService;
import com.senac.controle_financeiro.domain.entities.TipoDespesa;
import com.senac.controle_financeiro.domain.repository.TipoDespesaRepository;
import com.senac.controle_financeiro.domain.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipoDespesaService implements IDespesaService {

    @Autowired
    private TipoDespesaRepository tipoDespesaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public DespesaResponse salvar(DespesaRequest entrada) {

        var usuarioLogado = usuarioRepository.findById(entrada.usuario())
                .orElseThrow(() -> new RuntimeException("Erro ao encontrar o usuario"));

        var salvo = tipoDespesaRepository.save( new TipoDespesa(entrada, usuarioLogado));

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
    public List<DespesaResponse> listarTodos(Long id) {

        var despesas = tipoDespesaRepository.findByUsuarioId(id);

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
