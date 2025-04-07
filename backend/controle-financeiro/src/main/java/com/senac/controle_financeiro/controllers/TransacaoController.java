package com.senac.controle_financeiro.controllers;

import com.senac.controle_financeiro.dto.TransacaoDTO;
import com.senac.controle_financeiro.models.entities.Transacao;
import com.senac.controle_financeiro.models.entities.Usuario;
import com.senac.controle_financeiro.models.repository.TransacaoRepository;
import com.senac.controle_financeiro.models.repository.UsuarioRepository;
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

    @PostMapping
    public ResponseEntity<?> salvar(@RequestBody TransacaoDTO entrada) {

        Usuario usuario = usuarioRepository.findById(entrada.getUsuario())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado"));

        Transacao transacao = new Transacao();
        transacao.setValor(entrada.getValor());
        transacao.setEstabelecimento(entrada.getEstabelecimento());
        transacao.setTipoDespesa(entrada.getTipoDespesa());
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

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody TransacaoDTO entrada) {

        Optional<Transacao> atualizacao = transacaoRepository.findById(id);

        if (!atualizacao.isEmpty()) {

            Transacao transacao = atualizacao.get();
            transacao.setValor(entrada.getValor());
            transacao.setEstabelecimento(entrada.getEstabelecimento());
            transacao.setTipoDespesa(entrada.getTipoDespesa());
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
