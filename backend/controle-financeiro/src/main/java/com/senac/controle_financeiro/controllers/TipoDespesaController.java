package com.senac.controle_financeiro.controllers;

import com.senac.controle_financeiro.models.entities.TipoDespesa;
import com.senac.controle_financeiro.models.repository.TipoDespesaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tipo_despesa")
public class TipoDespesaController {

    @Autowired
    private TipoDespesaRepository tipoDespesaRepository;

    @PostMapping
    public ResponseEntity<?> salvar(@RequestBody TipoDespesa tipoDespesa){

        var retornoDespesa = tipoDespesaRepository.save(tipoDespesa);

        return ResponseEntity.ok().body(retornoDespesa);
    }

    @GetMapping("/listar/{id}")
    public Optional<TipoDespesa> listar (@PathVariable Long id) {

        Optional<TipoDespesa> listaDespesas = tipoDespesaRepository.findByUsuarioId(id);

        return listaDespesas;
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar (@PathVariable Long id, @RequestBody TipoDespesa tipoDespesaAtualizado){

        Optional<TipoDespesa> atualizacao = tipoDespesaRepository.findById(id);

        if (!atualizacao.isEmpty()){

            var retornoDespesa = tipoDespesaRepository.save(tipoDespesaAtualizado);

            return ResponseEntity.ok().body(retornoDespesa);
        }

        return null;
    }

    @DeleteMapping("/deletar/{id}")
    public void deletar (@PathVariable Long id) {

        Optional<TipoDespesa> existe = tipoDespesaRepository.findById(id);

        if (!existe.isEmpty()){
            tipoDespesaRepository.deleteById(id);
        }

    }
}
