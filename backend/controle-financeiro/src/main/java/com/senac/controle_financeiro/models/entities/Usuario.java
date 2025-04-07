package com.senac.controle_financeiro.models.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
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

}
