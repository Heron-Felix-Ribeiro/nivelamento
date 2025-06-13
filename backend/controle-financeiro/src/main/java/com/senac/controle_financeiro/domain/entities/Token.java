package com.senac.controle_financeiro.domain.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Optional;

@Entity
@Table(name = "token")
@Getter
@Setter
public class Token {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String token;
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    public Token(String token, Usuario usuario) {
    }

}
