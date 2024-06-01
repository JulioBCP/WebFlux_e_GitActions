package com.fiap.finance.model;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CarteiraTest {

    @Test
    public void adicionarAcao_DeveAdicionarAcaoNaCarteira() {
        List<Acao> acoes = Arrays.asList(
                new Acao("AAPL", 150.0),
                new Acao("GOOGL", 2500.0)
        );

        Carteira carteira = new Carteira("Minha Carteira", acoes);

        Acao novaAcao = new Acao("TSLA", 700.0);
        carteira.adicionarAcao(novaAcao);

        assertEquals(3, carteira.getAcoes().size());
    }
}
