package com.senac.controle_financeiro.domain.entities;

import com.senac.controle_financeiro.application.object.usuario.UsuarioRequest;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter @Entity @Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String usuario;
    @Column
    private Double salario;
    @Column
    private Integer idade;
    @Column
    private String senha;
    @Column
    private String cep;
    @Column
    private String estado;
    @Column
    private String cidade;
    @Column
    private String bairro;
    @Column
    private String rua;
    @Column
    private Integer numero;
    @OneToMany(mappedBy = "usuario")
    private List<Transacao> transacoes;
    @OneToMany(mappedBy = "usuario")
    private List<TipoDespesa> despesas;

    public Usuario() {}

    public Usuario(UsuarioRequest entrada) {

        this.id = entrada.id();
        this.usuario = entrada.usuario();
        this.salario = entrada.salario();
        this.idade = entrada.idade();
        this.senha = entrada.senha();
        this.cep = entrada.cep();
        this.estado = entrada.estado();
        this.cidade = entrada.cidade();
        this.bairro = entrada.bairro();
        this.rua = entrada.rua();
        this.numero = entrada.numero();

    }
}
