package com.senac.controle_financeiro.models.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @Entity @Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String usuario;
    @Column
    private Integer idade;
    @Column
    private String senha;
    @Column
    private String cep;
    @Column
    private String estado;
    @Column
    private String municipio;
    @Column
    private String bairro;
    @Column
    private String rua;
    @Column
    private Integer numero;


}
