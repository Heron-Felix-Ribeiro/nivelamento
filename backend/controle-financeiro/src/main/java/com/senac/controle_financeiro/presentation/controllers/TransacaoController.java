package com.senac.controle_financeiro.presentation.controllers;

import com.senac.controle_financeiro.application.object.TransacaoDTO;
import com.senac.controle_financeiro.application.object.transacao.TransacaoRequest;
import com.senac.controle_financeiro.application.object.transacao.TransacaoResponse;
import com.senac.controle_financeiro.application.services.TransacaoService;
import com.senac.controle_financeiro.domain.entities.TipoDespesa;
import com.senac.controle_financeiro.domain.entities.Transacao;
import com.senac.controle_financeiro.domain.entities.Usuario;
import com.senac.controle_financeiro.domain.repository.TipoDespesaRepository;
import com.senac.controle_financeiro.domain.repository.TransacaoRepository;
import com.senac.controle_financeiro.domain.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/transacao")
public class TransacaoController {

    @Autowired
    private TransacaoService transacaoService;

    @PostMapping
    public ResponseEntity<?> salvar(@RequestBody TransacaoRequest entrada) {

        var retornoTransacao = transacaoService.criarTransacao(entrada);

        return ResponseEntity.ok().body(retornoTransacao);
    }

    @GetMapping("/listar/{id}")
    public List<TransacaoResponse> listar(@PathVariable Long id) {

        List<TransacaoResponse> listaTransacoes = transacaoService.listarTodasTransacoes(id);

        return listaTransacoes;

    }

    @GetMapping("/listarUm/{id}")
    public ResponseEntity<?> listarUm (@PathVariable Long id) {

        var retornoTransacacao = transacaoService.listarTransacaoPorId(id);

        return ResponseEntity.ok().body(retornoTransacacao);

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody TransacaoRequest entrada) {

         var atualizacao = transacaoService.transacaoEditada(entrada);

        return ResponseEntity.ok().body(atualizacao);
    }

    @DeleteMapping("/deletar/{id}")
    public void deletar(@PathVariable Long id) {

        transacaoService.deletarTransacao(id);

    }
}
