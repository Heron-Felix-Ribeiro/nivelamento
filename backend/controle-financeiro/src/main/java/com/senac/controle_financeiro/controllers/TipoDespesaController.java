package com.senac.controle_financeiro.controllers;

import com.senac.controle_financeiro.dto.TipoDespesaDTO;
import com.senac.controle_financeiro.models.entities.TipoDespesa;
import com.senac.controle_financeiro.models.entities.Usuario;
import com.senac.controle_financeiro.models.repository.TipoDespesaRepository;
import com.senac.controle_financeiro.models.repository.UsuarioRepository;
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
    private TipoDespesaRepository tipoDespesaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping
    public ResponseEntity<?> salvar(@RequestBody TipoDespesaDTO entrada){

        Usuario usuario = usuarioRepository.findById(entrada.getUsuario())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado"));

        TipoDespesa tipoDespesa = new TipoDespesa();
        tipoDespesa.setDespesa(entrada.getNome());
        tipoDespesa.setUsuario(usuario);

        TipoDespesa despesa = tipoDespesaRepository.save(tipoDespesa);
        TipoDespesaDTO retornoDespesa = new TipoDespesaDTO(despesa);

        return ResponseEntity.ok().body(retornoDespesa);
    }

    @GetMapping("/listar/{id}")
    public List<TipoDespesaDTO> listar (@PathVariable Long id) {

        List<TipoDespesaDTO> listaDespesas = tipoDespesaRepository.findByUsuarioId(id)
                .stream()
                .map(TipoDespesaDTO::new)
                .collect(Collectors.toList());

        return listaDespesas;
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar (@PathVariable Long id, @RequestBody TipoDespesaDTO entrada){

        Optional<TipoDespesa> atualizacao = tipoDespesaRepository.findById(id);

        if (!atualizacao.isEmpty()){

            TipoDespesa despesa = atualizacao.get();
            despesa.setDespesa(entrada.getNome());
            tipoDespesaRepository.save(despesa);

            TipoDespesaDTO retornoDespesa = new TipoDespesaDTO(despesa);

            return ResponseEntity.ok().body(retornoDespesa);
        }

        return null;
    }

    @DeleteMapping("/deletar/{id}")
    public void deletar (@PathVariable Long id) {

        Optional<TipoDespesa> existe = tipoDespesaRepository.findById(id);

        if (!existe.isEmpty()){
            tipoDespesaRepository.deleteById(id);
        }

    }
}
