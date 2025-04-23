package com.senac.controle_financeiro.application.services.interfaces;

import com.senac.controle_financeiro.application.object.transacao.TransacaoCreateResponse;
import com.senac.controle_financeiro.application.object.transacao.TransacaoRequest;
import com.senac.controle_financeiro.application.object.transacao.TransacaoResponse;
import com.senac.controle_financeiro.application.object.transacao.TranscacaoEditResponse;

import java.util.List;

public interface ITransacaoService {

    TransacaoCreateResponse criarTransacao(TransacaoRequest transacaoRequest);

    TransacaoResponse listarTransacaoPorId(Long id);

    List<TransacaoResponse> listarTodasTransacoes(Long id);

    TranscacaoEditResponse transacaoEditada (TransacaoRequest transacaoRequest);

    Long deletarTransacao(Long id);


}
