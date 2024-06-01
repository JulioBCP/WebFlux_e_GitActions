package com.fiap.finance.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AcaoTest {

    @Test
    public void criarAcao_DeveDefinirSimboloEPrecoCorretamente() {
        String simbolo = "AAPL";
        double preco = 150.0;

        Acao acao = new Acao(simbolo, preco);

        assertEquals(simbolo, acao.getSimbolo());
        assertEquals(preco, acao.getPreco(), 0.01);
    }
}
