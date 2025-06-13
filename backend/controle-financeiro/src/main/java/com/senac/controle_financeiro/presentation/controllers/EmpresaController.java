package com.senac.controle_financeiro.presentation.controllers;

import com.senac.controle_financeiro.application.object.empresa.EmpresaRequest;
import com.senac.controle_financeiro.application.services.EmpresaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/empresa")
@Tag(name = "Controlador de Empresas", description = "Endereço responsável por controlar requisições ligadas a uma empresa ou mais empresas")
public class EmpresaController {

    @Autowired
    private EmpresaService empresaService;

    @PostMapping
    @Operation(summary = "Cadastro de empresa", description = "Recebe os dados de entrada, válida e salva no banco")
    public ResponseEntity<?> salvar(@RequestBody EmpresaRequest entrada) {
        try {
            var empresa = empresaService.salvar(entrada);
            return ResponseEntity.ok(empresa);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/listarTodos")
    @Operation(summary = "Lista todas empresas ativas", description = "Busca no banco todas empresas que possuam o booleano estaAtivo como verdadeiro")
    public ResponseEntity<?> listarTodos() {
        try {
            var empresas = empresaService.listarTodosAtivos();
            return ResponseEntity.ok(empresas);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e);
        }
    }

    @GetMapping("/listarInativo")
    @Operation(summary = "Lista todos empresas desativadas do sistema", description = "Busca no banco todas empresas que possuam o booleano estaAtivo como falso")
    public ResponseEntity<?> listarInativos() {
        try {
            var empresas = empresaService.listarTodosInativos();
            return ResponseEntity.ok(empresas);
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e);
        }
    }

    @GetMapping("/listarUm/{id}")
    @Operation(summary = "Lista apenas uma empresa", description = "Recebe o id de uma empresa e com base nisso busca no banco e carrega seus dados")
    public ResponseEntity<?> listarUm(@PathVariable Long id) {
        try {
            var empresa = empresaService.listarPorId(id);
            return ResponseEntity.ok(empresa);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e);
        }
    }

    @GetMapping("/quantidade")
    @Operation(summary = "Contabiliza o total de empresas", description = "Consulta o banco para saber quantas empresas com o booleano estaAtivo existem")
    public ResponseEntity<?> contarAtivos(){
        try {
            var retorno = empresaService.contarTodasAtivas();
            return ResponseEntity.ok(retorno);
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/editar/{id}")
    @Operation(summary = "Edita uma empresa já existente", description = "Recebe os dados, válida e salva em uma empresa já existente")
    public ResponseEntity<?> editar(@PathVariable Long id, @RequestBody EmpresaRequest entrada) {
        try {
            var empresa = empresaService.editar(id, entrada);
            return ResponseEntity.ok(empresa);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/mudarStatus/{id}")
    @Operation(summary = "Muda o status de uma empresa", description = "Recebe o id de uma empresa busca no banco, então muda o seu valor estaAtivo para o oposto")
    public ResponseEntity<?> mudarStatus(@PathVariable Long id) {
        try {
            var empresa = empresaService.mudarStatus(id);
            return ResponseEntity.ok(empresa);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
