package com.senac.controle_financeiro.application.services.interfaces;

import com.senac.controle_financeiro.application.object.transacao.TransacaoCreateResponse;
import com.senac.controle_financeiro.application.object.transacao.TransacaoRequest;
import com.senac.controle_financeiro.application.object.transacao.TransacaoResponse;
import com.senac.controle_financeiro.application.object.transacao.TranscacaoEditResponse;

import java.util.List;
import java.util.Stack;

public interface ITransacaoService {

    TransacaoCreateResponse criarTransacao(TransacaoRequest transacaoRequest);

    TransacaoResponse listarTransacaoPorId(Long id);

    List<TransacaoResponse> listarTodasTransacoes(String  cnpj);

    Double totalTransacoes(String cnpj);

    Stack<TransacaoResponse> maioresTransacoes(String cnpj);

    TranscacaoEditResponse transacaoEditada (TransacaoRequest transacaoRequest);

    Long deletarTransacao(Long id);


}
