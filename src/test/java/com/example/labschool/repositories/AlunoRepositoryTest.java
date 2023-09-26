package com.example.labschool.repositories;

import com.example.labschool.dtos.AlunoSeletorDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class AlunoRepositoryTest {

    @Autowired
    private AlunoRepository alunoRepository;

    @Test
    public void findByIdNameTest() {
        List<AlunoSeletorDto> retorno = alunoRepository.findByIdName();
        Assertions.assertFalse(retorno.isEmpty());
    }

}
