package com.senac.controle_financeiro.application.services;

import com.senac.controle_financeiro.application.object.transacao.TransacaoCreateResponse;
import com.senac.controle_financeiro.application.object.transacao.TransacaoRequest;
import com.senac.controle_financeiro.application.object.transacao.TransacaoResponse;
import com.senac.controle_financeiro.application.object.transacao.TranscacaoEditResponse;
import com.senac.controle_financeiro.application.services.interfaces.ITransacaoService;
import com.senac.controle_financeiro.domain.entities.Transacao;
import com.senac.controle_financeiro.domain.repository.EmpresaRepository;
import com.senac.controle_financeiro.domain.repository.TipoDespesaRepository;
import com.senac.controle_financeiro.domain.repository.TransacaoRepository;
import com.senac.controle_financeiro.domain.repository.UsuarioRepository;
import com.senac.controle_financeiro.domain.valueObjects.CNPJ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

@Service
public class TransacaoService implements ITransacaoService {

    @Autowired
    private TransacaoRepository transacaoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private EmpresaRepository empresaRepository;

    @Autowired
    private TipoDespesaRepository tipoDespesaRepository;

    @Autowired
    private UsuarioService usuarioService;



    @Override
    public TransacaoCreateResponse criarTransacao(TransacaoRequest transacaoRequest) {
        var empresa = empresaRepository.findByCnpj(transacaoRequest.cnpj())
                .orElseThrow(() -> new RuntimeException("Empresa não encontrada"));

        var usuarioLogado = usuarioService.usuarioLogado();

        var tipoDespesa = tipoDespesaRepository.findByDespesa(transacaoRequest.despesa())
                .orElseThrow(() -> new RuntimeException("Erro ao encontrar o tipo de despesa"));

        if (usuarioLogado != null && tipoDespesa != null) {

            transacaoRepository.save(new Transacao(transacaoRequest, usuarioLogado, tipoDespesa, empresa));

            return new TransacaoCreateResponse(
                    transacaoRequest.valor(),
                    transacaoRequest.estabelecimento(),
                    transacaoRequest.despesa(),
                    transacaoRequest.responsavel()
            );
        }

        throw new RuntimeException("Erro ao criar a transação");
    }

    @Override
    public TransacaoResponse listarTransacaoPorId(Long id) {
        var transacao = transacaoRepository.findById(id);

        if (transacao.isEmpty()) {
            throw new RuntimeException("Erro ao encontrar a transação");
        }
        var transacaoResponse = transacao.get();

        return new TransacaoResponse(
                transacaoResponse.getId(),
                transacaoResponse.getValor(),
                transacaoResponse.getEstabelecimento(),
                transacaoResponse.getDespesa().getDespesa(),
                transacaoResponse.getUsuario().getId()
        );

    }

    @Override
    public List<TransacaoResponse> listarTodasTransacoes(String cnpj) {
        var empresa = empresaRepository.findByCnpj(cnpj)
                .orElseThrow(() -> new RuntimeException("Empresa não encontrada"));

        var transacoes = transacaoRepository.findByEmpresaId(empresa.getId());

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
    public Double totalTransacoes(String cnpj) {
        var empresa = empresaRepository.findByCnpj(cnpj)
                .orElseThrow(() -> new RuntimeException("Empresa não encontrada"));

        var transacoes = transacaoRepository.findByEmpresaId(empresa.getId());

        return transacoes.stream()
                .mapToDouble(Transacao::getValor)
                .sum();
    }

    @Override
    public Stack<TransacaoResponse> maioresTransacoes(String cnpj) {
        var transacoes = transacaoRepository.findByEmpresaCnpj(new CNPJ(cnpj));

        Stack<TransacaoResponse> pilha = new Stack<>();
        transacoes.stream()
                .sorted((t1, t2) -> Double.compare(t2.getValor(), t1.getValor()))
                .limit(3)
                .map(transacao -> new TransacaoResponse(
                        transacao.getId(),
                        transacao.getValor(),
                        transacao.getEstabelecimento(),
                        transacao.getDespesa().getDespesa(),
                        transacao.getUsuario().getId()))
                .forEach(pilha::push);

        return pilha;
    }

    @Override
    public TranscacaoEditResponse transacaoEditada(TransacaoRequest transacaoRequest) {
        var transacao = transacaoRepository.findById(transacaoRequest.id())
                .orElseThrow(() -> new RuntimeException("Erro ao encontrar a transação"));

        if (transacao != null) {
            var transacaoSalva = transacao;
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
        var existe = transacaoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Erro ao encontrar a transação"));

        transacaoRepository.deleteById(id);
        return id;
    }
}
