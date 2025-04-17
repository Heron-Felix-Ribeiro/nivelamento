package com.senac.controle_financeiro.presentation.controllers;

import com.senac.controle_financeiro.application.object.usuario.UsuarioRequest;
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

    @GetMapping("/listar")
    public ResponseEntity<?> listar() {

        var usuario = usuarioService.usuarioLogado();

        return ResponseEntity.ok().body(usuario);
    }

    @GetMapping("/listar/{id}")
    public ResponseEntity<?> listarUm(@PathVariable Long id) {

        var retornoUsuario = usuarioService;

        return ResponseEntity.ok().body(retornoUsuario);
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody Usuario usuarioAtualizado) {
//
//        var atualizacao = usuarioRepository.findById(id)
//                .orElse(null);
//
//        if (atualizacao != null) {
//
//            var retornoUsuario = usuarioRepository.save(usuarioAtualizado);
//
//            return ResponseEntity.ok().body(retornoUsuario);
//        }
//
//        throw new RuntimeException("Informações do usuário não estão digitadas corretamente");
//    }


//    @DeleteMapping("/deletar/{id}")
//    public void deletar(@PathVariable Long id) {
//
//        var usuario = usuarioRepository.findById(id);
//
//        if (usuario != null) {
//            usuarioRepository.deleteById(id);
//        }
//
//    }

}
