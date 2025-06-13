package com.senac.controle_financeiro.presentation.controllers;

import com.senac.controle_financeiro.application.object.despesa.DespesaRequest;
import com.senac.controle_financeiro.application.services.TipoDespesaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tipo_despesa")
@Tag(name = "Controlador de Tipos de Despesa", description = "Endereço responsável por operações relacionadas com tipo de despesa" )
public class TipoDespesaController {

    @Autowired
    private TipoDespesaService tipoDespesaService;

    @PostMapping
    @Operation(summary = "Cria um novo tipo de despesa", description = "Recebe os dados, válida e salva uma nova despesa")
    public ResponseEntity<?> salvar(@RequestBody DespesaRequest entrada){

        try {
            var retornoDespesa = tipoDespesaService.salvar(entrada);
            return ResponseEntity.ok().body(retornoDespesa);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/listar/{cnpj}")
    @Operation(summary = "Lista tipos de despesa", description = "Lista tipos de despesa relacionados com o CNPJ recebido")
    public ResponseEntity<?> listar (@PathVariable String cnpj) {

        try {
            var listaDespesas = tipoDespesaService.listarTodos(cnpj);
            return ResponseEntity.ok().body(listaDespesas);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("listarUm/{id}")
    @Operation(summary = "Lista um tipo de despesa", description = "Lista um tipo de despesa através de seu id ")
    public ResponseEntity<?> listarUm (@PathVariable Long id) {

        try {
            var retorno = tipoDespesaService.listarPorId(id);
            return ResponseEntity.ok().body(retorno);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza um tipo de despesa", description = "Recebe os dados, valida e salva em um tipo de despesa já existente")
    public ResponseEntity<?> atualizar (@PathVariable Long id, @RequestBody DespesaRequest entrada){

        try {
            var atualizacao = tipoDespesaService.despesaEditada(entrada);
            return ResponseEntity.ok().body(atualizacao);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @DeleteMapping("/deletar/{id}")
    @Operation(summary = "Deleta um tipo de despesa", description = "Deleta um tipo de despessa com base no seu id")
    public ResponseEntity<?> deletar (@PathVariable Long id) {
        try {
            tipoDespesaService.deletar(id);
            return ResponseEntity.ok().body("Tipo de despesa excluído com sucesso");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }
}
