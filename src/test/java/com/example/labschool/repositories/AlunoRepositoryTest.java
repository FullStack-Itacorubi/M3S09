package com.example.labschool.repositories;

import com.example.labschool.dtos.AlunoSeletorDto;
import com.example.labschool.fixtures.AlunoFixture;
import com.example.labschool.models.AlunoModel;
import jakarta.validation.ConstraintViolationException;
import net.datafaker.Faker;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Locale;

@ActiveProfiles("test")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class AlunoRepositoryTest {

    private Faker faker;

    private Integer quantidade;
    private List<AlunoModel> alunos;

    @Autowired
    private AlunoRepository alunoRepository;

    @BeforeEach
    public void beforeEach() {
        faker = new Faker(new Locale("pt-BR"));
        quantidade = faker.number().numberBetween(1, 100);
        alunos = AlunoFixture.criarAlunosValidos(quantidade);

        alunoRepository.saveAllAndFlush(alunos);
    }

    @Test
    public void findByIdNameTest() {
        List<AlunoSeletorDto> retorno = alunoRepository.findByIdName();
        Assertions.assertTrue(retorno.size() == quantidade);
    }

    @Test
    public void findAllTest() {
        List<AlunoModel> todosAlunos = alunoRepository.findAll();
        Assertions.assertFalse(todosAlunos.isEmpty());
        Assertions.assertTrue(todosAlunos.size() == quantidade);
    }

    @Test
    public void findByIdTest() {
        AlunoModel aluno = alunos.get(0);
        AlunoModel alunoEncontrado = alunoRepository.findById(aluno.getId()).orElse(null);

        Assertions.assertEquals(aluno.getId(),             alunoEncontrado.getId());
        Assertions.assertEquals(aluno.getNome(),           alunoEncontrado.getNome());
        Assertions.assertEquals(aluno.getTelefone(),       alunoEncontrado.getTelefone());
        Assertions.assertEquals(aluno.getDataNascimento(), alunoEncontrado.getDataNascimento());
        Assertions.assertEquals(aluno.getCpf(),            alunoEncontrado.getCpf());
        Assertions.assertEquals(aluno.getNota(),           alunoEncontrado.getNota());
    }

    @Test
    public void saveTest() {
        AlunoModel alunoNovo = AlunoFixture.criarAlunoValido();

        alunoRepository.saveAndFlush(alunoNovo);

        List<AlunoModel> todosAlunos = alunoRepository.findAll();

        Assertions.assertNotNull(alunoNovo.getId());
        Assertions.assertTrue(todosAlunos.size() == quantidade + 1);
    }

    @Nested
    class ColumnNameTest {

        @Test
        public void notEmptyExceptionTest() {
            AlunoModel aluno = AlunoFixture.criarAlunoValido();
            aluno.setNome("");

            Assertions.assertThrows(ConstraintViolationException.class, () -> {
                alunoRepository.saveAndFlush(aluno);
            });
        }

        @Test
        public void notNullExceptionTest() {
            AlunoModel aluno = AlunoFixture.criarAlunoValido();
            aluno.setNome(null);

            Assertions.assertThrows(ConstraintViolationException.class, () -> {
                alunoRepository.saveAndFlush(aluno);
            });
        }

        @Test
        public void exceededLengthExceptionTest() {
            AlunoModel aluno = AlunoFixture.criarAlunoValido();
            aluno.setNome(faker.lorem().characters(300));

            Assertions.assertThrows(DataIntegrityViolationException.class, () -> {
                alunoRepository.saveAndFlush(aluno);
            });
        }
    }

    @Nested
    class ColumnTelefoneTest {

        @Test
        public void notEmptyExceptionTest() {
            AlunoModel aluno = AlunoFixture.criarAlunoValido();
            aluno.setTelefone("");

            Assertions.assertThrows(ConstraintViolationException.class, () -> {
                alunoRepository.saveAndFlush(aluno);
            });
        }

        @Test
        public void notNullExceptionTest() {
            AlunoModel aluno = AlunoFixture.criarAlunoValido();
            aluno.setTelefone(null);

            Assertions.assertThrows(ConstraintViolationException.class, () -> {
                alunoRepository.saveAndFlush(aluno);
            });
        }

        @Test
        public void exceededLengthExceptionTest() {
            AlunoModel aluno = AlunoFixture.criarAlunoValido();
            aluno.setTelefone(faker.lorem().characters(30));

            Assertions.assertThrows(DataIntegrityViolationException.class, () -> {
                alunoRepository.saveAndFlush(aluno);
            });
        }
    }

    @Nested
    class ColumnDataNascimentoTest {
        @Test
        public void notNullExceptionTest() {
            AlunoModel aluno = AlunoFixture.criarAlunoValido();
            aluno.setDataNascimento(null);

            Assertions.assertThrows(DataIntegrityViolationException.class, () -> {
                alunoRepository.saveAndFlush(aluno);
            });
        }
    }

    @Nested
    class ColumnCpfTest {

        @Test
        public void notEmptyExceptionTest() {
            AlunoModel aluno = AlunoFixture.criarAlunoValido();
            aluno.setCpf("");

            Assertions.assertThrows(ConstraintViolationException.class, () -> {
                alunoRepository.saveAndFlush(aluno);
            });
        }

        @Test
        public void notNullExceptionTest() {
            AlunoModel aluno = AlunoFixture.criarAlunoValido();
            aluno.setCpf(null);

            Assertions.assertThrows(ConstraintViolationException.class, () -> {
                alunoRepository.saveAndFlush(aluno);
            });
        }

        @Test
        public void exceededLengthExceptionTest() {
            AlunoModel aluno = AlunoFixture.criarAlunoValido();
            aluno.setCpf(faker.lorem().characters(20));

            Assertions.assertThrows(DataIntegrityViolationException.class, () -> {
                alunoRepository.saveAndFlush(aluno);
            });
        }
    }

    @Nested
    class ColumnNotaTest {
        @Test
        public void notNullExceptionTest() {
            AlunoModel aluno = AlunoFixture.criarAlunoValido();
            aluno.setNota(null);

            Assertions.assertThrows(DataIntegrityViolationException.class, () -> {
                alunoRepository.saveAndFlush(aluno);
            });
        }

        @Test
        public void rangeExceptionTest() {
            AlunoModel aluno = AlunoFixture.criarAlunoValido();
            aluno.setNota(faker.number().numberBetween(101, 1000));

            Assertions.assertThrows(ConstraintViolationException.class, () -> {
                alunoRepository.saveAndFlush(aluno);
            });
        }
    }

}
