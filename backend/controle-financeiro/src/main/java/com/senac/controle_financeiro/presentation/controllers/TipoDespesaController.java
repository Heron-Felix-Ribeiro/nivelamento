package com.senac.controle_financeiro.presentation.controllers;

import com.senac.controle_financeiro.application.object.despesa.DespesaRequest;
import com.senac.controle_financeiro.application.object.despesa.DespesaResponse;
import com.senac.controle_financeiro.application.services.TipoDespesaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tipo_despesa")
public class TipoDespesaController {

    @Autowired
    private TipoDespesaService tipoDespesaService;

    @PostMapping
    public ResponseEntity<?> salvar(@RequestBody DespesaRequest entrada){

        try {
            var retornoDespesa = tipoDespesaService.salvar(entrada);
            return ResponseEntity.ok().body(retornoDespesa);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/listar/{id}")
    public ResponseEntity<?> listar (@PathVariable Long id) {

        try {
            var listaDespesas = tipoDespesaService.listarTodos(id);
            return ResponseEntity.ok().body(listaDespesas);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("listarUm/{id}")
    public ResponseEntity<?> listarUm (@PathVariable Long id) {

        try {
            var retorno = tipoDespesaService.listarPorId(id);
            return ResponseEntity.ok().body(retorno);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar (@PathVariable Long id, @RequestBody DespesaRequest entrada){

        try {
            var atualizacao = tipoDespesaService.despesaEditada(entrada);
            return ResponseEntity.ok().body(atualizacao);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<?> deletar (@PathVariable Long id) {

        try {
            tipoDespesaService.deletar(id);
            return ResponseEntity.ok().body("Tipo de despesa excluído com sucesso");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }
}
