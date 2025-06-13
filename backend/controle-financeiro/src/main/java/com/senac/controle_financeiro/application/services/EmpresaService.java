package com.senac.controle_financeiro.application.services;

import com.senac.controle_financeiro.application.object.empresa.EmpresaRequest;
import com.senac.controle_financeiro.application.object.empresa.EmpresaResponse;
import com.senac.controle_financeiro.application.services.interfaces.IEmpresaService;
import com.senac.controle_financeiro.domain.entities.Empresa;
import com.senac.controle_financeiro.domain.entities.Endereco;
import com.senac.controle_financeiro.domain.repository.CidadeRepository;
import com.senac.controle_financeiro.domain.repository.EmpresaRepository;
import com.senac.controle_financeiro.domain.repository.EnderecoRepository;
import com.senac.controle_financeiro.domain.repository.UsuarioRepository;
import com.senac.controle_financeiro.domain.valueObjects.CNPJ;
import com.senac.controle_financeiro.domain.valueObjects.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpresaService implements IEmpresaService {

    @Autowired
    private EmpresaRepository empresaRepository;

    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private AuthService authService;

    @Override
    public EmpresaResponse salvar(EmpresaRequest entrada) {
        var cidade = cidadeRepository.findByIbge(entrada.ibge())
                .orElseThrow(() -> new RuntimeException("Cidade não encontrada"));
        var saida = empresaRepository.save(new Empresa(entrada));
        enderecoRepository.save(new Endereco(entrada, cidade, saida));

        return EmpresaResponse.deEntidade(saida);
    }

    @Override
    public List<EmpresaResponse> listarTodosAtivos() {
        var empresas = empresaRepository.findByEstaAtivo(true);
        return empresas.stream()
                .map(EmpresaResponse::deEntidade)
                .toList();
    }

    @Override
    public List<EmpresaResponse> listarTodosInativos() {
        var empresas = empresaRepository.findByEstaAtivo(false);
        return empresas.stream()
                .map(EmpresaResponse::deEntidade)
                .toList();
    }

    @Override
    public EmpresaResponse listarPorId(Long id) {
        var empresa = empresaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Empresa não encontrada"));
        return EmpresaResponse.deEntidade(empresa);
    }

    @Override
    public Integer contarTodasAtivas() {
        var quantidade = empresaRepository.countByEstaAtivoTrue();
        return quantidade;
    }

    @Override
    public EmpresaResponse editar(Long id, EmpresaRequest entrada) {
        var empresa = empresaRepository.findById(id).orElseThrow(() -> new RuntimeException("Empresa não encontrada"));

        empresa.setNome(entrada.nome());
        empresa.setEmail(new Email(entrada.email()));
        empresa.setVerba(entrada.verba());
        var retorno = empresaRepository.save(empresa);

        return EmpresaResponse.deEntidade(retorno);
    }

    @Override
    public Void mudarStatus(Long id) {
        var empresa = empresaRepository.findById(id).orElseThrow(() -> new RuntimeException("Empresa não encontrada"));
        empresa.setEstaAtivo(!empresa.isEstaAtivo());
        empresaRepository.save(empresa);

        var usuariosEmpresa = usuarioRepository.findByEmpresaId(empresa.getId());
        for(int i=0 ; i < usuariosEmpresa.size(); i++){
            var usuario = usuariosEmpresa.get(i);
            usuario.setEstaAtivo(false);
            usuarioRepository.save(usuario);
        }

        return null;
    }
}
