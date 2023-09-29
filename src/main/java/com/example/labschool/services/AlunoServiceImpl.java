package com.example.labschool.services;

import com.example.labschool.dtos.AlunoDto;
import com.example.labschool.dtos.AlunoSeletorDto;
import com.example.labschool.models.AlunoModel;
import com.example.labschool.repositories.AlunoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AlunoServiceImpl implements AlunoService {

    private static Logger LOGGER = LoggerFactory.getLogger(AlunoServiceImpl.class);

    @Autowired
    private AlunoRepository alunoRepository;

    @Override
    public AlunoModel save(AlunoDto dto) throws Exception {
        return save(null, dto);
    }

    @Override
    public AlunoModel save(UUID id, AlunoDto dto) throws Exception {
        AlunoModel alunoModel = new AlunoModel();
        if (id != null) {
            alunoModel = findById(id);
        }

        BeanUtils.copyProperties(dto, alunoModel);
        LOGGER.info("Instância Aluno cadastrada/alterada: " + dto.nome());

        return alunoRepository.save(alunoModel);
    }

    @Override
    public List<AlunoModel> findAll() {
        return alunoRepository.findAll();
    }

    @Override
    public AlunoModel findById(UUID id) {
        return alunoRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Aluno não encontrado!")
        );
    }

    @Override
    public List<AlunoSeletorDto> findAllIdNome() {
        return alunoRepository.findByIdName();
    }

    @Override
    public boolean deleteById(UUID id) {
        AlunoModel aluno = findById(id);
        try {
            alunoRepository.delete(aluno);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
