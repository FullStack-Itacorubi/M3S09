package com.example.labschool.dtos;

import com.example.labschool.models.AlunoModel;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public record AlunoDto(
        
        @NotEmpty(message = "O campo Nome é obrigatório.") 
        String nome,
        
        @NotBlank(message = "O campo Telefone é obrigatório.") 
        @Size(min=15, max=16)
        String telefone,
        
        @NotNull(message = "O campo Data de nascimento é obrigatorio.")
        @Temporal(TemporalType.DATE)
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        @JsonFormat(pattern = "yyyy-MM-dd")
        Date dataNascimento,
        
        @NotBlank(message = "O campo é CPF obrigatorio") 
        @Size(min=14, max=14) 
        String cpf,
        
        @NotNull
        @Range(min = 0, max = 100)
        Integer nota
        ) {
    
       public AlunoDto(AlunoModel aluno){
           this(aluno.getNome(), aluno.getTelefone(), aluno.getDataNascimento(), 
                   aluno.getCpf(), aluno.getNota());
       }
    
}
