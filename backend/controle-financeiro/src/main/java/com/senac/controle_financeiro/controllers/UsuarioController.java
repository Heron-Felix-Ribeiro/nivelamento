package com.senac.controle_financeiro.controllers;

import com.senac.controle_financeiro.dto.LoginDTO;
import com.senac.controle_financeiro.dto.UsuarioDTO;
import com.senac.controle_financeiro.models.entities.Usuario;
import com.senac.controle_financeiro.models.repository.UsuarioRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuario")
@Tag(name = "Usuário",description = "Endereço responsável pelo controle de requisições do usuário")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping
    @Operation(summary = "Salvar usuário", description = "Método responsável por salvar um novo usuário")
    public ResponseEntity<?> salvar(@RequestBody Usuario usuario) {

        var retornoUsuario = usuarioRepository.save(usuario);

        return ResponseEntity.ok().body(retornoUsuario);

    }

    @PostMapping("/login")
    public ResponseEntity<?> login (@RequestBody LoginDTO usuario) {

        Optional<Usuario> usuarioSalvo = usuarioRepository.findByUsuarioIgnoreCase(usuario.getUsuario());

        if (usuarioSalvo.isPresent()) {
            Boolean logado = usuarioSalvo.get().getSenha().equals(usuario.getSenha());

            if (logado){
                return ResponseEntity.ok().body(new UsuarioDTO(usuarioSalvo.get()));
            }

            return ResponseEntity.status(401).body("Senha inválida.");

        }

        return ResponseEntity.status(401).body("Usuário inválido.");

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
