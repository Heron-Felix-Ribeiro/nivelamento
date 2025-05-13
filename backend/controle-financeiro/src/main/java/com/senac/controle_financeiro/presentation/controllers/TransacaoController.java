package com.senac.controle_financeiro.presentation.controllers;

import com.senac.controle_financeiro.application.object.transacao.TransacaoRequest;
import com.senac.controle_financeiro.application.object.transacao.TransacaoResponse;
import com.senac.controle_financeiro.application.services.TransacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transacao")
public class TransacaoController {

    @Autowired
    private TransacaoService transacaoService;

    @PostMapping
    public ResponseEntity<?> salvar(@RequestBody TransacaoRequest entrada) {

        try {
            var retornoTransacao = transacaoService.criarTransacao(entrada);
            return ResponseEntity.ok().body(retornoTransacao);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/listar/{id}")
    public ResponseEntity<?> listar(@PathVariable Long id) {

        try {
            List<TransacaoResponse> listaTransacoes = transacaoService.listarTodasTransacoes(id);
            return ResponseEntity.ok().body(listaTransacoes);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @GetMapping("/listarUm/{id}")
    public ResponseEntity<?> listarUm (@PathVariable Long id) {

        try {
            var retornoTransacacao = transacaoService.listarTransacaoPorId(id);
            return ResponseEntity.ok().body(retornoTransacacao);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @GetMapping("/total/{id}")
    public ResponseEntity<?> totalTransacoes(@PathVariable Long id) {

        try {
            var retornoTransacao = transacaoService.totalTransacoes(id);
            return ResponseEntity.ok().body(retornoTransacao);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody TransacaoRequest entrada) {

        try {
            var atualizacao = transacaoService.transacaoEditada(entrada);
            return ResponseEntity.ok().body(atualizacao);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id) {

        try {
            transacaoService.deletarTransacao(id);
            return ResponseEntity.ok().body("Transação deletada com sucesso");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }
}
