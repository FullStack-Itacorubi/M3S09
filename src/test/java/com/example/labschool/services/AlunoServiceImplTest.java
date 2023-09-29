package com.example.labschool.services;

import com.example.labschool.dtos.AlunoDto;
import com.example.labschool.fixtures.AlunoFixture;
import com.example.labschool.models.AlunoModel;
import com.example.labschool.repositories.AlunoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AlunoServiceImplTest {

    @InjectMocks
    AlunoServiceImpl alunoService;

    @Mock
    AlunoRepository alunoRepository;

    @Test
    public void saveCreateTest() throws Exception {
        AlunoModel alunoModel = AlunoFixture.criarAlunoValido();
        AlunoDto alunoDto = new AlunoDto(alunoModel);

        when(alunoRepository.save(any())).thenReturn(alunoModel);

        AlunoModel alunoSalvo = alunoService.save(alunoDto);

        assertNotNull(alunoSalvo);
    }

    @Test
    public void saveUpdateTest() throws Exception {
        UUID id = UUID.randomUUID();
        AlunoModel alunoModel = AlunoFixture.criarAlunoValido();
        alunoModel.setId(id);

        AlunoDto alunoDto = new AlunoDto(alunoModel);

        when(alunoRepository.findById(id)).thenReturn(Optional.of(alunoModel));
        when(alunoRepository.save(any())).thenReturn(alunoModel);

        AlunoModel alunoSalvo = alunoService.save(id, alunoDto);

        assertNotNull(alunoSalvo);
    }

    @Test
    public void findByIdTest() {
        UUID id = UUID.randomUUID();

        when(alunoRepository.findById(id)).thenReturn(Optional.of(AlunoFixture.criarAlunoValido()));

        assertNotNull(alunoService.findById(id));
    }

    @Test
    public void findByIdNotFoundTest() {
        when(alunoRepository.findById(any())).thenReturn(Optional.empty());

        assertThrowsExactly(EntityNotFoundException.class, () -> {
            alunoService.findById(UUID.randomUUID());
        });
    }

}
