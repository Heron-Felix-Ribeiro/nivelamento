package com.senac.controle_financeiro.application.services;

import com.senac.controle_financeiro.application.object.usuario.UsuarioRequest;
import com.senac.controle_financeiro.application.services.interfaces.IUsuarioService;
import com.senac.controle_financeiro.domain.entities.Usuario;
import com.senac.controle_financeiro.domain.repository.EmpresaRepository;
import com.senac.controle_financeiro.domain.repository.UsuarioRepository;
import com.senac.controle_financeiro.application.object.usuario.UsuarioResponse;
import com.senac.controle_financeiro.domain.valueObjects.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService implements IUsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private EmpresaRepository empresaRepository;

    @Autowired
    private AuthService authService;

    @Override
    public UsuarioResponse listarUm(Long id) {
        var usuarioLogado = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Erro ao encontrar o usuario"));

        return new UsuarioResponse(
                usuarioLogado.getId(),
                usuarioLogado.getUsuario(),
                usuarioLogado.getEmpresa().getNome(),
                usuarioLogado.getEmail().getEmail()
        );
    }

    public Usuario usuarioLogado(){
       var auth =  SecurityContextHolder.getContext().getAuthentication();

       var usuarioLogado = usuarioRepository.findByEmail(new Email(auth.getName()))
               .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

       return usuarioLogado;
    }

    @Override
    public Usuario salvar(UsuarioRequest entrada) {
        if (entrada.cnpj() == null){
            var usuarioSemEmpresa = usuarioRepository.findAll()
                    .stream()
                    .anyMatch(usuario -> usuario.getEmpresa() == null);

            if (usuarioSemEmpresa) {
                throw new RuntimeException("Já existe um usuário sem empresa cadastrada.");
            }

            var retornoAdmin = new Usuario(entrada, null);
            retornoAdmin.setAdmin(true);
            return usuarioRepository.save(retornoAdmin);
        }

        var empresa = empresaRepository.findByCnpj(entrada.cnpj().replaceAll("[^\\d]", ""))
                .orElseThrow(() -> new RuntimeException("Empresa não encontrada"));

        var emailRegistrado = usuarioRepository.existsByEmail(new Email(entrada.email()));
        if (emailRegistrado){
            throw new RuntimeException("Email já cadastrado");
        }

        var retorno = new Usuario(entrada, empresa);
        retorno.setAdmin(false);
        return usuarioRepository.save(retorno);
    }

    @Override
    public List<UsuarioResponse> listarTodosAtivos() {
        var usuarios = usuarioRepository.findByEstaAtivoTrueAndEmpresaIdNotNull();

        return usuarios.stream()
                .map(usuario -> new UsuarioResponse(
                        usuario.getId(),
                        usuario.getUsuario(),
                        usuario.getEmpresa().getNome(),
                        usuario.getEmail().getEmail()
                        ))
                .toList();
    }

    @Override
    public List<UsuarioResponse> listarTodosInativos() {
        var usuarios = usuarioRepository.findByEstaAtivoFalseAndEmpresaIdNotNull();

        return usuarios.stream()

                .map(usuario -> new UsuarioResponse(
                        usuario.getId(),
                        usuario.getUsuario(),
                        usuario.getEmpresa().getNome(),
                        usuario.getEmail().getEmail()
                ))
                .toList();
    }

    @Override
    public Integer contarUsuariosAtivos() {
        var quantidade = usuarioRepository.countByEstaAtivoTrue() - 1;

        return quantidade;
    }

    @Override
    public UsuarioResponse usuarioEditado(UsuarioRequest entrada) {
        var usuarios = usuarioRepository.findAll();
        var usuarioEncontrado = usuarioRepository.findById(entrada.id())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        usuarioEncontrado.setUsuario(entrada.usuario());
        usuarioEncontrado.setEmail(new Email(entrada.email()));
        var saida = usuarioRepository.save(usuarioEncontrado);

        return new UsuarioResponse(
                saida.getId(),
                saida.getUsuario(),
                saida.getEmpresa().getNome(),
                saida.getEmail().getEmail()
        );
    }

    @Override
    public Void mudarStatus(Long id) {
        var usuarios = usuarioRepository.findAll();
        var usuario = usuarioRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        usuario.setEstaAtivo(!usuario.getEstaAtivo());
        usuarioRepository.save(usuario);
        return null;
    }
}


