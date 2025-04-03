package com.senac.controle_financeiro.controllers;

import com.senac.controle_financeiro.models.entities.Usuario;
import com.senac.controle_financeiro.models.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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

    @GetMapping
    public List<Usuario> listar() {

        var retornoUsuario = usuarioRepository.findAll();

        return retornoUsuario;
    }

    @GetMapping("/{id}")
    public List<Usuario> listarUm(@PathVariable Long id) {

        var retornoUsuario = usuarioRepository.findById(id);

        return new ArrayList<Usuario>();
    }

    /*@PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody Usuario usuario) {

        var retornoUsuario = usuarioRepository.save(usuario);

        return ResponseEntity.ok();
    }*/

    /*@DeleteMapping("/{id}")
    public List<Usuario> deletar(@PathVariable Long id) {



        return new ArrayList<>();
    }*/

}
