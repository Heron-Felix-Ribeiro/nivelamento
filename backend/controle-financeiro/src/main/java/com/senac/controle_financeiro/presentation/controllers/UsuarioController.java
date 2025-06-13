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
    @Operation(summary = "Salvar usuário", description = "Recebe os dados de entrada, valida e salva no banco")
    public ResponseEntity<?> salvar(@RequestBody UsuarioRequest usuarioRequest) {
        try {
            var retornoUsuario = usuarioService.salvar(usuarioRequest);
            return ResponseEntity.ok().body(retornoUsuario);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/listarUm/{id}")
    @Operation(summary = "Listar um usuário", description = "Recebe o ID de um usuário e busca no banco")
    public ResponseEntity<?> listar(@PathVariable Long id) {
        try {
            var usuario = usuarioService.listarUm(id);
            return ResponseEntity.ok().body(usuario);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/listar")
    @Operation(summary = "Listar todos usuários ativos", description = "Busca no banco todos usuários que possuam o booleano estaAtivo como verdadeiro")
    public ResponseEntity<?> listarTodos() {
        try {
            var retornoUsuario = usuarioService.listarTodosAtivos();
            return ResponseEntity.ok().body(retornoUsuario);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/listarInativo")
    @Operation(summary = "Listar todos usuários inativos", description = "Busca no banco todos usuários que possuam o booleano estaAtivo como falso")
    public ResponseEntity<?> listaInativos() {
        try {
            var retorno = usuarioService.listarTodosInativos();
            return ResponseEntity.ok(retorno);
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/quantidade")
    @Operation(summary = "Contar usuários ativos", description = "Conta o número de usuários ativos no sistema")
    public ResponseEntity<?> contarAtivos() {
        try {
            var retorno = usuarioService.contarUsuariosAtivos();
            return ResponseEntity.ok(retorno);
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar usuário", description = "Recebe os dados de entrada, valida e salva em um usuário já existente")
    public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody UsuarioRequest usuarioAtualizado) {
        try {
            var atualizacao = usuarioService.usuarioEditado(usuarioAtualizado);
            return ResponseEntity.ok().body(atualizacao);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/mudarStatus/{id}")
    @Operation(summary = "Muda o status de um usuário", description = "Recebe o id de um usuario busca no banco, então muda o seu valor estaAtivo para o oposto")
    public ResponseEntity<?> deletar(@PathVariable Long id) {
        try {
            var usuario = usuarioService.mudarStatus(id);
            return ResponseEntity.ok().body("Exclusão realizada com sucesso");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

}
