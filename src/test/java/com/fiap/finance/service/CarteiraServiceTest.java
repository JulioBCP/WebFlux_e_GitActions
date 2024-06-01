package com.fiap.finance.service;

import com.fiap.finance.model.Acao;
import com.fiap.finance.model.Carteira;
import com.fiap.finance.repository.CarteiraRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CarteiraServiceTest {

    @InjectMocks
    private CarteiraService carteiraService;

    @Mock
    private CarteiraRepository carteiraRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCalculaRentabilidade() {
        List<Acao> acoes = Arrays.asList(
                new Acao("AAPL", 150.0),
                new Acao("GOOGL", 2500.0)
        );

        Carteira carteira = new Carteira("Minha Carteira", acoes);
        Mockito.when(carteiraRepository.findByNome("Minha Carteira")).thenReturn(Mono.just(carteira));

        StepVerifier.create(carteiraService.calcularRentabilidade(carteira.getNome()))
                .expectNext(acoes.stream().mapToDouble(Acao::getPreco).sum())
                .verifyComplete();
    }

    @Test
    public void testFindAll() {
        Carteira carteira1 = new Carteira("Carteira1", Arrays.asList());
        Carteira carteira2 = new Carteira("Carteira2", Arrays.asList());
        List<Carteira> carteiras = Arrays.asList(carteira1, carteira2);

        Mockito.when(carteiraRepository.findAll()).thenReturn(Flux.fromIterable(carteiras));

        Flux<Carteira> result = carteiraService.findAll();

        assertEquals(carteiras, result.collectList().block());
    }

    @Test
    public void testSave() {
        // Crie uma carteira fictícia com ações
        List<Acao> acoes = Arrays.asList(
                new Acao("AAPL", 150.0),
                new Acao("GOOGL", 2500.0));

        Carteira carteira = new Carteira("Minha Carteira", acoes);

        Mockito.when(carteiraRepository.save(Mockito.any(Carteira.class))).thenReturn(Mono.just(carteira));

        StepVerifier.create(carteiraService.save("Minha Carteira", carteira.getAcoes()))
                .expectNext(carteira)
                .verifyComplete();
    }
}
