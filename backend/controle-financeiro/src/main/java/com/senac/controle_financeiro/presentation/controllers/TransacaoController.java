package com.senac.controle_financeiro.presentation.controllers;

import com.senac.controle_financeiro.application.object.transacao.TransacaoRequest;
import com.senac.controle_financeiro.application.object.transacao.TransacaoResponse;
import com.senac.controle_financeiro.application.services.TransacaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transacao")
@Tag(name = "Controlador de Transações",  description = "Endereço responsável por controlar operações relacionadas com transações")
public class TransacaoController {

    @Autowired
    private TransacaoService transacaoService;

    @PostMapping
    @Operation(summary = "Cria uma nova transação", description = "Recebe os dados de entrada, valida e salva uma nova transação")
    public ResponseEntity<?> salvar(@RequestBody TransacaoRequest entrada) {
        try {
            var retornoTransacao = transacaoService.criarTransacao(entrada);
            return ResponseEntity.ok().body(retornoTransacao);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/listar/{cnpj}")
    @Operation(summary = "Lista todas transações", description = "Lista todas as transações relacionadas com o CNPJ recebido")
    public ResponseEntity<?> listar(@PathVariable String cnpj) {
        try {
            List<TransacaoResponse> listaTransacoes = transacaoService.listarTodasTransacoes(cnpj);
            return ResponseEntity.ok().body(listaTransacoes);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @GetMapping("/listarUm/{id}")
    @Operation(summary = "Lista uma transação", description = "Lista uma transação através de seu ID")
    public ResponseEntity<?> listarUm(@PathVariable Long id) {
        try {
            var retornoTransacacao = transacaoService.listarTransacaoPorId(id);
            return ResponseEntity.ok().body(retornoTransacacao);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @GetMapping("/total/{cnpj}")
    @Operation(summary = "Calcula o total de transações de uma empresa", description = "Calcula o total de transações relacionadas com o CNPJ recebido")
    public ResponseEntity<?> totalTransacoes(@PathVariable String cnpj) {
        try {
            var retornoTransacao = transacaoService.totalTransacoes(cnpj);
            return ResponseEntity.ok().body(retornoTransacao);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/maior/{cnpj}")
    @Operation(summary = "Lista as maiores transações de uma empresa", description = "Lista as maiores transações relacionadas com o CNPJ recebido")
    public ResponseEntity<?> maioresTransacoes(@PathVariable String cnpj) {
        try {
            var retornoTranscao = transacaoService.maioresTransacoes(cnpj);
            return ResponseEntity.ok().body(retornoTranscao);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza uma transação", description = "Recebe os dados de entrada, valida e salva em uma transação já existente")
    public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody TransacaoRequest entrada) {
        try {
            var atualizacao = transacaoService.transacaoEditada(entrada);
            return ResponseEntity.ok().body(atualizacao);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/deletar/{id}")
    @Operation(summary = "Deleta uma transação", description = "Deleta uma transação com base no seu ID")
    public ResponseEntity<?> deletar(@PathVariable Long id) {
        try {
            transacaoService.deletarTransacao(id);
            return ResponseEntity.ok().body("Transação deletada com sucesso");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
