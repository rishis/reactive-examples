package com.rishi.reactiveexamples;

import com.rishi.reactiveexamples.domain.Person;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PersonRepository {
    public Mono<Person> getById(Integer id);
    public Flux<Person> findAll();
}

