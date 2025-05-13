package com.senac.controle_financeiro.application.services;

import com.senac.controle_financeiro.application.object.transacao.TransacaoCreateResponse;
import com.senac.controle_financeiro.application.object.transacao.TransacaoRequest;
import com.senac.controle_financeiro.application.object.transacao.TransacaoResponse;
import com.senac.controle_financeiro.application.object.transacao.TranscacaoEditResponse;
import com.senac.controle_financeiro.application.services.interfaces.ITransacaoService;
import com.senac.controle_financeiro.domain.entities.Transacao;
import com.senac.controle_financeiro.domain.repository.TipoDespesaRepository;
import com.senac.controle_financeiro.domain.repository.TransacaoRepository;
import com.senac.controle_financeiro.domain.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransacaoService implements ITransacaoService {

    @Autowired
    private TransacaoRepository transacaoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TipoDespesaRepository tipoDespesaRepository;

    @Override
    public TransacaoCreateResponse criarTransacao(TransacaoRequest transacaoRequest) {

        var usuarioLogado = usuarioRepository.findById(transacaoRequest.usuario())
                .orElseThrow(() -> new RuntimeException("Erro ao encontrar o usuario"));

        var tipoDespesa = tipoDespesaRepository.findByDespesa(transacaoRequest.despesa())
                .orElseThrow(() -> new RuntimeException("Erro ao encontrar o tipo de despesa"));

        if (usuarioLogado != null && tipoDespesa != null) {

            transacaoRepository.save(new Transacao(transacaoRequest, usuarioLogado, tipoDespesa));

            return new TransacaoCreateResponse(
                    transacaoRequest.valor(),
                    transacaoRequest.estabelecimento(),
                    transacaoRequest.despesa()
            );
        }

        throw new RuntimeException("Erro ao criar a transação");
    }

    @Override
    public TransacaoResponse listarTransacaoPorId(Long id) {

        var transcao = transacaoRepository.findById(id);

        if (transcao.isEmpty()) {
            throw new RuntimeException("Erro ao encontrar a transação");
        }

        return new TransacaoResponse(
                transcao.get().getId(),
                transcao.get().getValor(),
                transcao.get().getEstabelecimento(),
                transcao.get().getDespesa().getDespesa(),
                transcao.get().getUsuario().getId()
        );

    }

    @Override
    public List<TransacaoResponse> listarTodasTransacoes(Long id) {

        var transacoes = transacaoRepository.findByUsuarioId(id);

        if (transacoes.isEmpty()) {
            throw new RuntimeException("Não há transações cadastradas");
        }

        return transacoes.stream()
                .map(transacao -> new TransacaoResponse(
                        transacao.getId(),
                        transacao.getValor(),
                        transacao.getEstabelecimento(),
                        transacao.getDespesa().getDespesa(),
                        transacao.getUsuario().getId()))
                .collect(Collectors.toList());
    }

    @Override
    public Double totalTransacoes(Long id) {
        var transacoes = transacaoRepository.findByUsuarioId(id);

        return transacoes.stream()
                .mapToDouble(Transacao::getValor)
                .sum();
    }

    @Override
    public List<TransacaoResponse> maioresTransacoes(Long id) {
        var transacoes = transacaoRepository.findByUsuarioId(id);

        if (transacoes.isEmpty()) {
            throw new RuntimeException("Não há transações cadastradas");
        }

        return transacoes.stream()
                .sorted((t1, t2) -> Double.compare(t2.getValor(), t1.getValor()))
                .limit(3)
                .map(transacao -> new TransacaoResponse(
                        transacao.getId(),
                        transacao.getValor(),
                        transacao.getEstabelecimento(),
                        transacao.getDespesa().getDespesa(),
                        transacao.getUsuario().getId()))
                .collect(Collectors.toList());
    }

    @Override
    public TranscacaoEditResponse transacaoEditada(TransacaoRequest transacaoRequest) {

        var transacao = transacaoRepository.findById(transacaoRequest.id());

        if (transacao.isPresent()) {
            var transacaoSalva = transacao.get();
            transacaoSalva.setValor(transacaoRequest.valor());
            transacaoSalva.setEstabelecimento(transacaoRequest.estabelecimento());
            var tipoDespesa = tipoDespesaRepository.findByDespesa(transacaoRequest.despesa())
                    .orElseThrow(() -> new RuntimeException("Erro ao encontrar o tipo de despesa"));
            transacaoSalva.setDespesa(tipoDespesa);
            transacaoRepository.save(transacaoSalva);

            return new TranscacaoEditResponse(
                    transacaoSalva.getValor(),
                    transacaoSalva.getEstabelecimento(),
                    transacaoSalva.getDespesa().getDespesa()
            );
        }

        throw new RuntimeException("Não foi possível editar a transação");
    }

    @Override
    public Long deletarTransacao(Long id) {

        var existe = transacaoRepository.findById(id);

        if (!existe.isEmpty()) {
            transacaoRepository.deleteById(id);
            return id;
        }
        throw new RuntimeException("Erro ao encontrar a transação");
    }
}
