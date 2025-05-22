package com.senac.controle_financeiro.presentation.controllers;

import com.senac.controle_financeiro.application.object.usuario.UsuarioRequest;
import com.senac.controle_financeiro.application.services.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
@Tag(name = "Usuário", description = "Endereço responsável pelo controle de requisições do usuário")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    @Operation(summary = "Salvar usuário", description = "Método responsável por salvar um novo usuário")
    public ResponseEntity<?> salvar(@RequestBody UsuarioRequest usuarioRequest) {
            try{
                var retornoUsuario = usuarioService.salvar(usuarioRequest);
                return ResponseEntity.ok().body(retornoUsuario);
            } catch (Exception e) {
                return ResponseEntity.badRequest().body("Erro ao salvar o usuário: " + e.getMessage());
            }
    }

    @GetMapping("/listarUm")
    public ResponseEntity<?> listar() {
        try {
            var usuario = usuarioService.usuarioLogado();
            return ResponseEntity.ok().body(usuario);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/listar")
    public ResponseEntity<?> listarTodos() {
        try {
            var retornoUsuario = usuarioService.listarTodos();
            return ResponseEntity.ok().body(retornoUsuario);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody UsuarioRequest usuarioAtualizado) {
        try {
            var atualizacao = usuarioService.usuarioEditado(usuarioAtualizado);
            return ResponseEntity.ok().body(atualizacao);
        } catch (Exception e) {
            return  ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id) {
        try {
            var usuario = usuarioService.deletar(id);
            return ResponseEntity.ok().body("Exclusão realizada com sucesso");
        } catch (Exception e) {
           return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

}
