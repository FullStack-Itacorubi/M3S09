package com.example.labschool.controllers;

import com.example.labschool.dtos.AlunoDto;
import com.example.labschool.fixtures.AlunoFixture;
import com.example.labschool.models.AlunoModel;
import com.example.labschool.repositories.AlunoRepository;
import com.example.labschool.utils.JsonUtil;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AlunoController.class)
public class AlunoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AlunoRepository alunoRepository;

    @Test
    public void saveAlunoTest() throws Exception {
        AlunoModel alunoModel = AlunoFixture.criarAlunoValido();
        AlunoDto requestDto = new AlunoDto(alunoModel);

        when(alunoRepository.save(any())).thenReturn(alunoModel);

        mockMvc.perform(
                post("/alunos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtil.objToJson(requestDto))
                )
                .andExpectAll(
                        status().isCreated(),
                        content().contentType(MediaType.APPLICATION_JSON),
                        jsonPath("$.nome").value(requestDto.nome()),
                        jsonPath("$.dataNascimento").value(requestDto.dataNascimento().toString()),
                        jsonPath("$.telefone").value(requestDto.telefone()),
                        jsonPath("$.cpf").value(requestDto.cpf()),
                        jsonPath("$.nota").value(requestDto.nota())
                );
    }

    @Test
    public void getAllAlunosTest() throws Exception {
        List<AlunoModel> alunos = AlunoFixture.criarAlunosValidos(2);
        AlunoModel aluno1 = alunos.get(0);
        AlunoModel aluno2 = alunos.get(1);

        when(alunoRepository.findAll()).thenReturn(alunos);

        mockMvc.perform(get("/alunos"))
                .andExpectAll(
                        status().isOk(),
                        content().contentType(MediaType.APPLICATION_JSON),
                        jsonPath("$.[0].nome").value(aluno1.getNome()),
                        jsonPath("$.[0].dataNascimento").value(aluno1.getDataNascimento().toString()),
                        jsonPath("$.[0].telefone").value(aluno1.getTelefone()),
                        jsonPath("$.[0].cpf").value(aluno1.getCpf()),
                        jsonPath("$.[0].nota").value(aluno1.getNota()),
                        jsonPath("$.[1].nome").value(aluno2.getNome()),
                        jsonPath("$.[1].telefone").value(aluno2.getTelefone()),
                        jsonPath("$.[1].cpf").value(aluno2.getCpf()),
                        jsonPath("$.[1].nota").value(aluno2.getNota())
                );
    }

    @Test
    public void getOneAlunoTest() throws Exception {
        UUID id = UUID.randomUUID();

        AlunoModel aluno = AlunoFixture.criarAlunoValido();
        aluno.setId(id);

        when(alunoRepository.findById(id)).thenReturn(Optional.of(aluno));

        mockMvc.perform(get("/alunos/"+id))
                .andExpectAll(
                        status().isOk(),
                        content().contentType(MediaType.APPLICATION_JSON),
                        jsonPath("$.nome").value(aluno.getNome()),
                        jsonPath("$.dataNascimento").value(aluno.getDataNascimento().toString()),
                        jsonPath("$.telefone").value(aluno.getTelefone()),
                        jsonPath("$.cpf").value(aluno.getCpf()),
                        jsonPath("$.nota").value(aluno.getNota())
                );
    }

    @Test
    public void updateAlunoTest() throws Exception {
        UUID id = UUID.randomUUID();

        AlunoModel aluno = AlunoFixture.criarAlunoValido();
        aluno.setId(id);
        AlunoDto requestDto = new AlunoDto(aluno);

        when(alunoRepository.findById(id)).thenReturn(Optional.of(aluno));
        when(alunoRepository.save(any())).thenReturn(aluno);

        mockMvc.perform(
                        put("/alunos/"+id)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(JsonUtil.objToJson(requestDto))
                )
                .andExpectAll(
                        status().isOk(),
                        content().contentType(MediaType.APPLICATION_JSON),
                        jsonPath("$.nome").value(requestDto.nome()),
                        jsonPath("$.dataNascimento").value(requestDto.dataNascimento().toString()),
                        jsonPath("$.telefone").value(requestDto.telefone()),
                        jsonPath("$.cpf").value(requestDto.cpf()),
                        jsonPath("$.nota").value(requestDto.nota())
                );
    }

    @Nested
    class PostInvalidTest {

        @Test
        public void nomeInvalidTest() throws Exception {
            AlunoModel alunoModel = AlunoFixture.criarAlunoValido();
            alunoModel.setNome(null);
            AlunoDto requestDto = new AlunoDto(alunoModel);

            when(alunoRepository.save(any())).thenReturn(alunoModel);

            mockMvc.perform(
                            post("/alunos")
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .content(JsonUtil.objToJson(requestDto))
                    )
                    .andExpect(status().isBadRequest());
        }

        @Test
        public void telefoneInvalidTest() throws Exception {
            AlunoModel alunoModel = AlunoFixture.criarAlunoValido();
            alunoModel.setTelefone(null);
            AlunoDto requestDto = new AlunoDto(alunoModel);

            when(alunoRepository.save(any())).thenReturn(alunoModel);

            mockMvc.perform(
                            post("/alunos")
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .content(JsonUtil.objToJson(requestDto))
                    )
                    .andExpect(status().isBadRequest());
        }

        @Test
        public void dataNascimentoInvalidTest() throws Exception {
            AlunoModel alunoModel = AlunoFixture.criarAlunoValido();
            alunoModel.setDataNascimento(null);
            AlunoDto requestDto = new AlunoDto(alunoModel);

            when(alunoRepository.save(any())).thenReturn(alunoModel);

            mockMvc.perform(
                            post("/alunos")
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .content(JsonUtil.objToJson(requestDto))
                    )
                    .andExpect(status().isBadRequest());
        }

        @Test
        public void cpfInvalidTest() throws Exception {
            AlunoModel alunoModel = AlunoFixture.criarAlunoValido();
            alunoModel.setCpf(null);
            AlunoDto requestDto = new AlunoDto(alunoModel);

            when(alunoRepository.save(any())).thenReturn(alunoModel);

            mockMvc.perform(
                            post("/alunos")
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .content(JsonUtil.objToJson(requestDto))
                    )
                    .andExpect(status().isBadRequest());
        }

        @Test
        public void notaInvalidTest() throws Exception {
            AlunoModel alunoModel = AlunoFixture.criarAlunoValido();
            alunoModel.setNota(null);
            AlunoDto requestDto = new AlunoDto(alunoModel);

            when(alunoRepository.save(any())).thenReturn(alunoModel);

            mockMvc.perform(
                            post("/alunos")
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .content(JsonUtil.objToJson(requestDto))
                    )
                    .andExpect(status().isBadRequest());
        }

    }

}
