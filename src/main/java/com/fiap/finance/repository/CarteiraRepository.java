package com.fiap.finance.repository;

import com.fiap.finance.model.Carteira;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface CarteiraRepository extends ReactiveMongoRepository<Carteira, String> {

    Mono<Carteira> findByNome(String nome);

    Flux<Carteira> findAll();
}
