package com.senac.controle_financeiro.controllers;

import com.senac.controle_financeiro.models.entities.TipoDespesa;
import com.senac.controle_financeiro.models.entities.Usuario;
import com.senac.controle_financeiro.models.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping
    public ResponseEntity<?> salvar(@RequestBody Usuario usuario) {

        var retornoUsuario = usuarioRepository.save(usuario);

        return ResponseEntity.ok().body(retornoUsuario);

    }

    @GetMapping("/listar")
    public List<Usuario> listar() {

        var retornoUsuario = usuarioRepository.findAll();

        return retornoUsuario;
    }

    @GetMapping("/listar/{id}")
    public ResponseEntity<?> listarUm(@PathVariable Long id) {

        var retornoUsuario = usuarioRepository.findById(id);

        return ResponseEntity.ok().body(retornoUsuario);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody Usuario usuarioAtualizado) {

        Optional<Usuario> atualizacao = usuarioRepository.findById(id);

        if (!atualizacao.isEmpty()) {

            var retornoUsuario = usuarioRepository.save(usuarioAtualizado);

            return ResponseEntity.ok().body(retornoUsuario);
        }

        return null;
    }


    @DeleteMapping("/deletar/{id}")
    public void deletar(@PathVariable Long id) {

        Optional<Usuario> existe = usuarioRepository.findById(id);

        if (!existe.isEmpty()){
            usuarioRepository.deleteById(id);
        }

    }

}
