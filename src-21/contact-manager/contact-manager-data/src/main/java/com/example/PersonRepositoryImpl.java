package com.example;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class PersonRepositoryImpl implements PersonRepository {

    private final PersonJpaRepository jpaRepository;

    public PersonRepositoryImpl(PersonJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public void addPerson(Person person) {
        jpaRepository.save(PersonEntity.fromPerson(person));
    }

    @Override
    public List<Person> findAllPeople() {
        return jpaRepository.findAll().stream()
                .map(PersonEntity::toPerson)
                .collect(Collectors.toList());
    }

    @Override
    public Person findPersonByName(String firstName, String lastName) {
        return jpaRepository.findByFirstNameAndLastName(firstName, lastName)
                .map(PersonEntity::toPerson)
                .orElse(null);
    }

    @Override
    public void deletePerson(String firstName, String lastName) {
        jpaRepository.deleteByFirstNameAndLastName(firstName, lastName);
    }
}