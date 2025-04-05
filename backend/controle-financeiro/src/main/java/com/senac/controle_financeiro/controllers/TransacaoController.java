package com.senac.controle_financeiro.controllers;

import com.senac.controle_financeiro.models.entities.TipoDespesa;
import com.senac.controle_financeiro.models.entities.Transacao;
import com.senac.controle_financeiro.models.repository.TransacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/transacao")
public class TransacaoController {

    @Autowired
    private TransacaoRepository transacaoRepository;

    @PostMapping
    public ResponseEntity<?> salvar (@RequestBody Transacao transacao) {

        var retornoTransacao = transacaoRepository.save(transacao);

        return ResponseEntity.ok().body(retornoTransacao);
    }

    @GetMapping("/listar/{id}")
    public Optional<Transacao> listar(@PathVariable Long id) {

        Optional<Transacao> retornoTransacao = transacaoRepository.findByUsuarioId(id);

        return retornoTransacao;
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar (@PathVariable Long id, @RequestBody Transacao transacaoAtualizada) {

        Optional<Transacao> atualizacao = transacaoRepository.findById(id);

        if (!atualizacao.isEmpty()) {

            var retornoTransacao = transacaoRepository.save(transacaoAtualizada);

            return ResponseEntity.ok().body(retornoTransacao);
        }

        return null;
    }

    @DeleteMapping("/deletar/{id}")
    public void deletar (@PathVariable Long id) {

        Optional<Transacao> existe = transacaoRepository.findById(id);

        if (!existe.isEmpty()){
            transacaoRepository.deleteById(id);
        }
    }
}
