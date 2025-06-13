package com.senac.controle_financeiro.domain.entities;

import com.senac.controle_financeiro.application.object.empresa.EmpresaRequest;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity @Getter @Setter @Table(name = "endereco")
public class Endereco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String cep;
    @ManyToOne
    @JoinColumn(name = "cidade_id")
    private Cidade cidade;
    private String bairro;
    private String rua;
    private Integer numero;
    private String complemento;
    @ManyToOne
    @JoinColumn(name = "empresa_id")
    private Empresa empresa;

    public Endereco(EmpresaRequest request, Cidade cidade, Empresa empresa) {
        this.cep = request.cep();
        this.cidade = cidade;
        this.bairro = request.bairro();
        this.rua = request.rua();
        this.numero = request.numero();
        this.empresa = empresa;
    }


}
