package com.example.labschool.dtos;

import java.util.Date;
import java.util.UUID;

public record AcompanhamentoPedDto(UUID id, String titulo, String aluno, String pedagogo, 
        Date dataAcompanhamento, boolean finalizado) {

}
