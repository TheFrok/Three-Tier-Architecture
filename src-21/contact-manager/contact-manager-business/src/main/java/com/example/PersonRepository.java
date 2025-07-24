package com.example;

import java.util.List;

public interface PersonRepository {
    void addPerson(Person person);
    List<Person> findAllPeople();
    Person findPersonByName(String firstName, String lastName);
}
