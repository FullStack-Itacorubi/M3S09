package com.example.labschool.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

import java.util.Date;
import java.util.List;
import java.util.UUID;


@Entity
@Table(name = "TB_ALUNO")
public class AlunoModel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotBlank
    @Column(nullable = false)
    private String nome;

    @NotBlank
    @Column(length = 16, nullable = false)
    private String telefone;
    
    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date dataNascimento;

    @NotBlank
    @Column(length = 14, nullable = false)
    private String cpf;

    @Range(min = 0, max = 100)
    @Column(nullable = false)
    private Integer nota;
    
    @OneToMany(mappedBy = "aluno")
    private List<AcompanhamentoPedagogicoModel> acompanhamentoPedagogicoModel;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Integer getNota() {
        return nota;
    }

    public void setNota(Integer nota) {
        this.nota = nota;
    }
     
}
