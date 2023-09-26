package com.example.labschool.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "TB_ACOMPANHAMENTO")
public class AcompanhamentoPedagogicoModel {
   
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    
    @ManyToOne
    @JoinColumn(name = "aluno_id", nullable = false)
    private AlunoModel aluno;
    
    @ManyToOne
    @JoinColumn(name = "professor_id", nullable = false)
    private PedagogoModel pedagogo;
    
    
    @Temporal(TemporalType.DATE)
    private Date dataAcompanhamento;
    
    private String titulo;
    
    private String descricao;
    
    private boolean finalizado=false; 

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public AlunoModel getAluno() {
        return aluno;
    }

    public void setAluno(AlunoModel aluno) {
        this.aluno = aluno;
    }

    public PedagogoModel getPedagogo() {
        return pedagogo;
    }

    public void setPedagogo(PedagogoModel pedagogo) {
        this.pedagogo = pedagogo;
    }

    public Date getDataAcompanhamento() {
        return dataAcompanhamento;
    }

    public void setDataAcompanhamento(Date dataAcompanhamento) {
        this.dataAcompanhamento = dataAcompanhamento;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public boolean isFinalizado() {
        return finalizado;
    }

    public void setFinalizado(boolean finalizado) {
        this.finalizado = finalizado;
    }
    
}
