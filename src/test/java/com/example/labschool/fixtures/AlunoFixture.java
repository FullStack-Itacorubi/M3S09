package com.example.labschool.fixtures;

import com.example.labschool.dtos.AlunoDto;
import com.example.labschool.models.AlunoModel;
import net.datafaker.Faker;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class AlunoFixture {

    private static final Faker faker = new Faker(new Locale("pt-BR"));

    public static List<AlunoModel> criarAlunosValidos(Integer qtd) {
        List<AlunoModel> alunos = new ArrayList<>();
        for (int i = 0; i < qtd; i++) {
            alunos.add(criarAlunoValido());
        }
        return alunos;
    }

    public static AlunoModel criarAlunoValido() {
        AlunoModel aluno = new AlunoModel();
        aluno.setNome(faker.name().name());
        aluno.setTelefone(faker.phoneNumber().cellPhone());
        aluno.setDataNascimento(faker.date().birthday(10, 100));
        aluno.setCpf(faker.cpf().valid());
        aluno.setNota(faker.number().numberBetween(0, 10));

        return aluno;
    }

    public static AlunoDto criarAlunoDtoValido() {
        return new AlunoDto(criarAlunoValido());
    }
}
