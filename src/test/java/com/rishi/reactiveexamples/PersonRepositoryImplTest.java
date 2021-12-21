package com.rishi.reactiveexamples;

import com.rishi.reactiveexamples.domain.Person;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PersonRepositoryImplTest {

    PersonRepositoryImpl personRepository;
    @BeforeEach
    void setUp() {
        personRepository = new PersonRepositoryImpl();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getByIdBlock() {
        System.out.println("before**");
        Mono<Person> personMono = personRepository.getById(2);
        Person person = personMono.block();
        System.out.println("OP is "+person);
    }

    @Test
    void getByIdSubscribe() {
        System.out.println("before**");
        Mono<Person> personMono = personRepository.getById(1);
        personMono.subscribe(person -> {
           System.out.println("op in sub"+person.toString());
        });
        System.out.println("##**");
    }

    @Test
    void testFluxBlock() {
        Flux<Person> personFlux = personRepository.findAll();
        Person person = personFlux.blockFirst();
        System.out.println("Flux person "+person);
    }

    @Test
    void testFluxSubscribe() {
        Flux<Person> personFlux = personRepository.findAll();
        personFlux.subscribe(person -> {
            System.out.println("Flux subscribe"+person);
        });
        System.out.println("After subscribe method flux");

    }

    @Test
    void testFluxToList() {
        Flux<Person> personFlux = personRepository.findAll();
        Mono<List<Person>> personListMono = personFlux.collectList();
        personListMono.subscribe(list -> {
            list.forEach(person -> {
                System.out.println("testFluxList**"+person);
            });
        });
    }

    @Test
    void testFilter() {
        Flux<Person> personFlux = personRepository.findAll();
        Integer id = 20;
        Mono<Person> personMono = personFlux.filter(person -> person.getId() == id).single();
        personMono.doOnError(throwable -> {
            System.out.println("inside doOnError"+throwable);
        }).onErrorReturn(Person.builder().build()).subscribe(person -> {
            System.out.println("Filter -->"+person);
        });

    }
}