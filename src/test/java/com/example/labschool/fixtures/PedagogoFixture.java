package com.example.labschool.fixtures;

import com.example.labschool.models.PedagogoModel;
import net.datafaker.Faker;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class PedagogoFixture {

    private static final Faker faker = new Faker(new Locale("pt-BR"));

    public static List<PedagogoModel> criarPedagogosValidos(Integer qtd) {
        List<PedagogoModel> pedagogos = new ArrayList<>();
        for (int i = 0; i < qtd; i++) {
            pedagogos.add(criarPedagogoValido());
        }
        return pedagogos;
    }

    public static PedagogoModel criarPedagogoValido() {
        PedagogoModel pedagogo = new PedagogoModel();
        pedagogo.setNome(faker.name().name());
        pedagogo.setTelefone(faker.phoneNumber().cellPhone());
        pedagogo.setDataNascimento(faker.date().birthday(25, 90));
        pedagogo.setCpf(faker.cpf().valid());
        pedagogo.setEmail(faker.internet().emailAddress());
        pedagogo.setSenha(faker.lorem().characters(8));
        return pedagogo;
    }

}
