package com.fiap.finance.service;

import com.fiap.finance.model.Acao;
import com.fiap.finance.model.Carteira;
import com.fiap.finance.repository.CarteiraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class CarteiraService {

    @Autowired
    CarteiraRepository carteiraRepository;

    public Mono<Double> calcularRentabilidade(String carteiraNome) {
        return carteiraRepository.findByNome(carteiraNome)
                .map(carteira -> carteira.getAcoes().stream()
                .mapToDouble(acao -> acao.getPreco())
                .sum());
    }

    public Flux<Carteira> findAll() {
        return carteiraRepository.findAll();
    }

    public Mono<Carteira> save(String nomeCarteira, List<Acao> acoes) {
        var carteira = new Carteira(nomeCarteira, acoes);
        return carteiraRepository.save(carteira);
    }

}
