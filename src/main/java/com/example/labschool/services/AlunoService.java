package com.example.labschool.services;

import com.example.labschool.dtos.AlunoDto;
import com.example.labschool.dtos.AlunoSeletorDto;
import com.example.labschool.models.AlunoModel;

import java.util.List;
import java.util.UUID;

public interface AlunoService {

    AlunoModel save(AlunoDto dto) throws Exception;

    AlunoModel save(UUID id, AlunoDto dto) throws Exception;

    List<AlunoModel> findAll();

    AlunoModel findById(UUID id);

    List<AlunoSeletorDto> findAllIdNome();

    boolean deleteById(UUID id);

    List<AlunoModel> findByNome(String nome);
}
