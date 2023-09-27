package com.example.labschool.repositories;

import com.example.labschool.dtos.PedagogoSeletorDto;
import com.example.labschool.fixtures.PedagogoFixture;
import com.example.labschool.fixtures.PedagogoFixture;
import com.example.labschool.models.PedagogoModel;
import com.example.labschool.models.PedagogoModel;
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

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PedagogoRepositoryTest {

    private static final Faker faker = new Faker(new Locale("pt-BR"));

    private Integer quantidade;
    private List<PedagogoModel> pedagogos;

    @Autowired PedagogoRepository pedagogoRepository;

    @BeforeEach
    public void beforeEach() {
        quantidade = faker.random().nextInt(1, 30);
        pedagogos = PedagogoFixture.criarPedagogosValidos(quantidade);
        pedagogoRepository.saveAllAndFlush(pedagogos);
    }

    @Test
    public void saveTest() {
        PedagogoModel pedagogo = PedagogoFixture.criarPedagogoValido();
        pedagogoRepository.saveAndFlush(pedagogo);

        assertNotNull(pedagogo.getId());
    }

    @Test
    public void findAllTest() {
        List<PedagogoModel> pedagogosExistentes = pedagogoRepository.findAll();

        assertNotNull(pedagogosExistentes);
        assertFalse(pedagogosExistentes.isEmpty());
        assertEquals(quantidade, pedagogosExistentes.size());
    }

    @Test
    public void findByIdTest() {
        PedagogoModel pedagogo = pedagogos.get(0);
        PedagogoModel pedagogoEncontrado = pedagogoRepository.findById(pedagogo.getId()).orElse(null);

        assertEquals(pedagogo.getId(),             pedagogoEncontrado.getId());
        assertEquals(pedagogo.getNome(),           pedagogoEncontrado.getNome());
        assertEquals(pedagogo.getTelefone(),       pedagogoEncontrado.getTelefone());
        assertEquals(pedagogo.getDataNascimento(), pedagogoEncontrado.getDataNascimento());
        assertEquals(pedagogo.getCpf(),            pedagogoEncontrado.getCpf());
        assertEquals(pedagogo.getEmail(),          pedagogoEncontrado.getEmail());
        assertEquals(pedagogo.getSenha(),          pedagogoEncontrado.getSenha());
    }

    @Test
    public void findByIdNameTest() {
        List<PedagogoSeletorDto> retorno = pedagogoRepository.findByIdName();
        Assertions.assertTrue(retorno.size() == quantidade);
    }

    @Nested
    class ColumnNameTest {

        @Test
        public void notEmptyExceptionTest() {
            PedagogoModel pedagogo = PedagogoFixture.criarPedagogoValido();
            pedagogo.setNome("");

            Assertions.assertThrows(ConstraintViolationException.class, () -> {
                pedagogoRepository.saveAndFlush(pedagogo);
            });
        }

        @Test
        public void notNullExceptionTest() {
            PedagogoModel pedagogo = PedagogoFixture.criarPedagogoValido();
            pedagogo.setNome(null);

            Assertions.assertThrows(ConstraintViolationException.class, () -> {
                pedagogoRepository.saveAndFlush(pedagogo);
            });
        }

        @Test
        public void exceededLengthExceptionTest() {
            PedagogoModel pedagogo = PedagogoFixture.criarPedagogoValido();
            pedagogo.setNome(faker.lorem().characters(300));

            Assertions.assertThrows(DataIntegrityViolationException.class, () -> {
                pedagogoRepository.saveAndFlush(pedagogo);
            });
        }
    }

    @Nested
    class ColumnTelefoneTest {

        @Test
        public void notEmptyExceptionTest() {
            PedagogoModel pedagogo = PedagogoFixture.criarPedagogoValido();
            pedagogo.setTelefone("");

            Assertions.assertThrows(ConstraintViolationException.class, () -> {
                pedagogoRepository.saveAndFlush(pedagogo);
            });
        }

        @Test
        public void notNullExceptionTest() {
            PedagogoModel pedagogo = PedagogoFixture.criarPedagogoValido();
            pedagogo.setTelefone(null);

            Assertions.assertThrows(ConstraintViolationException.class, () -> {
                pedagogoRepository.saveAndFlush(pedagogo);
            });
        }

        @Test
        public void exceededLengthExceptionTest() {
            PedagogoModel pedagogo = PedagogoFixture.criarPedagogoValido();
            pedagogo.setTelefone(faker.lorem().characters(30));

            Assertions.assertThrows(DataIntegrityViolationException.class, () -> {
                pedagogoRepository.saveAndFlush(pedagogo);
            });
        }
    }

    @Nested
    class ColumnDataNascimentoTest {
        @Test
        public void notNullExceptionTest() {
            PedagogoModel pedagogo = PedagogoFixture.criarPedagogoValido();
            pedagogo.setDataNascimento(null);

            Assertions.assertThrows(DataIntegrityViolationException.class, () -> {
                pedagogoRepository.saveAndFlush(pedagogo);
            });
        }
    }

    @Nested
    class ColumnCpfTest {

        @Test
        public void notEmptyExceptionTest() {
            PedagogoModel pedagogo = PedagogoFixture.criarPedagogoValido();
            pedagogo.setCpf("");

            Assertions.assertThrows(ConstraintViolationException.class, () -> {
                pedagogoRepository.saveAndFlush(pedagogo);
            });
        }

        @Test
        public void notNullExceptionTest() {
            PedagogoModel pedagogo = PedagogoFixture.criarPedagogoValido();
            pedagogo.setCpf(null);

            Assertions.assertThrows(ConstraintViolationException.class, () -> {
                pedagogoRepository.saveAndFlush(pedagogo);
            });
        }

        @Test
        public void exceededLengthExceptionTest() {
            PedagogoModel pedagogo = PedagogoFixture.criarPedagogoValido();
            pedagogo.setCpf(faker.lorem().characters(20));

            Assertions.assertThrows(DataIntegrityViolationException.class, () -> {
                pedagogoRepository.saveAndFlush(pedagogo);
            });
        }
    }

    @Nested
    class ColumnEmailTest {

        @Test
        public void notEmptyExceptionTest() {
            PedagogoModel pedagogo = PedagogoFixture.criarPedagogoValido();
            pedagogo.setEmail("");

            Assertions.assertThrows(ConstraintViolationException.class, () -> {
                pedagogoRepository.saveAndFlush(pedagogo);
            });
        }

        @Test
        public void notNullExceptionTest() {
            PedagogoModel pedagogo = PedagogoFixture.criarPedagogoValido();
            pedagogo.setEmail(null);

            Assertions.assertThrows(ConstraintViolationException.class, () -> {
                pedagogoRepository.saveAndFlush(pedagogo);
            });
        }

        @Test
        public void notEmailExceptionTest() {
            PedagogoModel pedagogo = PedagogoFixture.criarPedagogoValido();
            pedagogo.setEmail(faker.lorem().characters(300));

            Assertions.assertThrows(ConstraintViolationException.class, () -> {
                pedagogoRepository.saveAndFlush(pedagogo);
            });
        }

        @Test
        public void exceededLengthExceptionTest() {
            PedagogoModel pedagogo = PedagogoFixture.criarPedagogoValido();
            pedagogo.setEmail(
                    faker.lorem().characters(300) +
                    faker.internet().emailAddress()
            );

            Assertions.assertThrows(ConstraintViolationException.class, () -> {
                pedagogoRepository.saveAndFlush(pedagogo);
            });
        }
    }

    @Nested
    class ColumnSenhaTest {

        @Test
        public void notEmptyExceptionTest() {
            PedagogoModel pedagogo = PedagogoFixture.criarPedagogoValido();
            pedagogo.setSenha("");

            Assertions.assertThrows(ConstraintViolationException.class, () -> {
                pedagogoRepository.saveAndFlush(pedagogo);
            });
        }

        @Test
        public void notNullExceptionTest() {
            PedagogoModel pedagogo = PedagogoFixture.criarPedagogoValido();
            pedagogo.setSenha(null);

            Assertions.assertThrows(ConstraintViolationException.class, () -> {
                pedagogoRepository.saveAndFlush(pedagogo);
            });
        }

        @Test
        public void exceededLengthExceptionTest() {
            PedagogoModel pedagogo = PedagogoFixture.criarPedagogoValido();
            pedagogo.setSenha(faker.lorem().characters(20));

            Assertions.assertThrows(DataIntegrityViolationException.class, () -> {
                pedagogoRepository.saveAndFlush(pedagogo);
            });
        }
    }

}
