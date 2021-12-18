package com.rishi.reactiveexamples;

import com.rishi.reactiveexamples.domain.Person;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;

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
        Mono<Person> personMono = personRepository.getById(1);
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
}