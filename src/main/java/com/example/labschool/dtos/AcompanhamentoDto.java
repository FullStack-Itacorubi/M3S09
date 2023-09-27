package com.example.labschool.dtos;

import com.example.labschool.models.AlunoModel;
import com.example.labschool.models.PedagogoModel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

public record AcompanhamentoDto(
            @NotNull AlunoModel aluno,
            @NotNull PedagogoModel pedagogo,
            @NotNull Date dataAcompanhamento,
            @NotBlank String titulo,
            @NotBlank String descricao,
            boolean finalizado
        ) {
}
