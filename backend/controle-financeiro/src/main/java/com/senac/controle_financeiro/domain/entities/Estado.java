package com.senac.controle_financeiro.domain.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity @Getter @Setter @Table(name = "estado")
public class Estado {
    @Id
    private Long id;
    private String nome;
    private String uf;
}
