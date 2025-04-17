package com.senac.controle_financeiro.presentation.controllers;

import com.senac.controle_financeiro.application.object.TransacaoDTO;
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
    private TransacaoRepository transacaoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TipoDespesaRepository tipoDespesaRepository;

    @PostMapping
    public ResponseEntity<?> salvar(@RequestBody TransacaoDTO entrada) {

        Usuario usuario = usuarioRepository.findById(entrada.getUsuario())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado"));

        TipoDespesa despesa = tipoDespesaRepository.findByDespesa(entrada.getDespesa())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Despesa não encontrada"));

        Transacao transacao = new Transacao();
        transacao.setValor(entrada.getValor());
        transacao.setEstabelecimento(entrada.getEstabelecimento());
        transacao.setDespesa(despesa);
        transacao.setUsuario(usuario);

        Transacao transacaoSalva = transacaoRepository.save(transacao);
        TransacaoDTO retornoTransacao= new TransacaoDTO(transacaoSalva);

        return ResponseEntity.ok().body(retornoTransacao);
    }

    @GetMapping("/listar/{id}")
    public List<TransacaoDTO> listar(@PathVariable Long id) {

        List<TransacaoDTO> listaTransacoes = transacaoRepository.findByUsuarioId(id)
                .stream()
                .map(TransacaoDTO::new)
                .collect(Collectors.toList());

        return listaTransacoes;

    }

    @GetMapping("/listarUm/{id}")
    public ResponseEntity<?> listarUm (@PathVariable Long id) {

        var retornoTransacacao = transacaoRepository.findById(id);

        return ResponseEntity.ok().body(retornoTransacacao);

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody TransacaoDTO entrada) {

        Optional<Transacao> atualizacao = transacaoRepository.findById(id);

        if (!atualizacao.isEmpty()) {

            Transacao transacao = atualizacao.get();
            transacao.setValor(entrada.getValor());
            transacao.setEstabelecimento(entrada.getEstabelecimento());
            TipoDespesa despesa = tipoDespesaRepository.findByDespesa(entrada.getDespesa())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Despesa não encontrada"));
            transacao.setDespesa(despesa);
            transacaoRepository.save(transacao);

            TransacaoDTO retornoTransacao = new TransacaoDTO(transacao);

            return ResponseEntity.ok().body(retornoTransacao);
        }

        return null;
    }

    @DeleteMapping("/deletar/{id}")
    public void deletar(@PathVariable Long id) {

        Optional<Transacao> existe = transacaoRepository.findById(id);

        if (!existe.isEmpty()) {
            transacaoRepository.deleteById(id);
        }
    }
}
