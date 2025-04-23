package com.senac.controle_financeiro.presentation.controllers;

import com.senac.controle_financeiro.application.object.TipoDespesaDTO;
import com.senac.controle_financeiro.application.object.despesa.DespesaRequest;
import com.senac.controle_financeiro.application.object.despesa.DespesaResponse;
import com.senac.controle_financeiro.application.services.TipoDespesaService;
import com.senac.controle_financeiro.domain.entities.TipoDespesa;
import com.senac.controle_financeiro.domain.entities.Usuario;
import com.senac.controle_financeiro.domain.repository.TipoDespesaRepository;
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
@RequestMapping("/tipo_despesa")
public class TipoDespesaController {

    @Autowired
    private TipoDespesaService tipoDespesaService;

    @PostMapping
    public ResponseEntity<?> salvar(@RequestBody DespesaRequest entrada){

        var retornoDespesa = tipoDespesaService.salvar(entrada);

        return ResponseEntity.ok().body(retornoDespesa);
    }

    @GetMapping("/listar/{id}")
    public List<DespesaResponse> listar (@PathVariable Long id) {

        var listaDespesas = tipoDespesaService.listarTodos(id);

        return listaDespesas;
    }

    @GetMapping("listarUm/{id}")
    public ResponseEntity<?> listarUm (@PathVariable Long id) {

        var retorno = tipoDespesaService.listarPorId(id);

        return ResponseEntity.ok().body(retorno);

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar (@PathVariable Long id, @RequestBody DespesaRequest entrada){

        var atualizacao = tipoDespesaService.despesaEditada(entrada);

        return null;
    }

    @DeleteMapping("/deletar/{id}")
    public void deletar (@PathVariable Long id) {

        tipoDespesaService.deletar(id);

    }
}
