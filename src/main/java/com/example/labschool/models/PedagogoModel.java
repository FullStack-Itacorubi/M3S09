package com.example.labschool.models;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name="TB_PEDAGOGO")
public class PedagogoModel {
   
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private UUID id;
   
   private String nome;
   
   private String telefone;
   
   @Temporal(TemporalType.DATE)
   private Date dataNascimento;
   
   private String cpf;
   
   private String email;
   
   private String senha;
   
   @OneToMany(mappedBy = "pedagogo")
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
