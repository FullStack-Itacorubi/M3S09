package com.example.labschool.controllers;

import com.example.labschool.fixtures.AlunoFixture;
import com.example.labschool.models.AlunoModel;
import com.example.labschool.repositories.AlunoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AlunoControllerIT {

    private String nome;
    private AlunoModel aluno;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private AlunoRepository alunoRepository;

    @BeforeEach
    public void beforeEach() {
        aluno = AlunoFixture.criarAlunoValido();
        nome = aluno.getNome();
        alunoRepository.saveAndFlush(aluno);
    }

    @Test
    public void findByName() {
        ResponseEntity response = testRestTemplate.getForEntity("/alunos/nome/" + nome, AlunoModel[].class);

        AlunoModel[] alunos = (AlunoModel[]) response.getBody();

        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertTrue(response.hasBody());
        assertEquals(alunos[0].getNome(), nome);
    }

}
