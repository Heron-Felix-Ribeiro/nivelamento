package com.senac.controle_financeiro.presentation.controllers;

import com.senac.controle_financeiro.application.object.usuario.UsuarioRequest;
import com.senac.controle_financeiro.application.object.usuario.UsuarioResponse;
import com.senac.controle_financeiro.domain.entities.Usuario;
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


            var retornoUsuario = usuarioService.salvar(usuarioRequest);

            return ResponseEntity.ok().body(retornoUsuario);


    }

    @GetMapping("/listarUm")
    public ResponseEntity<?> listar() {

        var usuario = usuarioService.usuarioLogado();

        return ResponseEntity.ok().body(usuario);
    }

    @GetMapping("/listar")
    public ResponseEntity<?> listarTodos() {

        var retornoUsuario = usuarioService.listarTodos();

        return ResponseEntity.ok().body(retornoUsuario);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody UsuarioRequest usuarioAtualizado) {

        var atualizacao = usuarioService.usuarioEditado(usuarioAtualizado);

        return ResponseEntity.ok().body(atualizacao);

    }

    @DeleteMapping("/deletar/{id}")
    public void deletar(@PathVariable Long id) {

        var usuario = usuarioService.deletar(id);

    }

}
