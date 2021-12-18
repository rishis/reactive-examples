package com.rishi.reactiveexamples;

import com.rishi.reactiveexamples.domain.Person;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class PersonRepositoryImpl implements PersonRepository {

    Person rishi = new Person(1,"Rishi","Shehrawat");
    Person aima = new Person(2,"Aima","Shehrawat");
    Person gayatri = new Person(3,"Gayatri","Handique");

    @Override
    public Mono<Person> getById(Integer id) {
        return Mono.just(aima);
    }

    @Override
    public Flux<Person> findAll() {
        return Flux.just(rishi,aima,gayatri);
    }
}
