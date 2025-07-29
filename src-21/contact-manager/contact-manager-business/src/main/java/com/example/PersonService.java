package com.example;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.regex.Pattern;

@Service
public class PersonService {

    private static final Pattern EMAIL_PATTERN = Pattern.compile(
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" +
            "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");

    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public void addPerson(Person person) {
        if (person.firstName() == null || person.firstName().isEmpty() ||
            person.lastName() == null || person.lastName().isEmpty() ||
            person.email() == null || person.email().isEmpty()) {
            throw new IllegalArgumentException("Missing required fields");
        }

        if (!EMAIL_PATTERN.matcher(person.email()).matches()) {
            throw new IllegalArgumentException("Invalid email format");
        }

        personRepository.addPerson(person);
    }

    public List<Person> findAllPeople() {
        return personRepository.findAllPeople();
    }

    public List<Person> findAllPeopleSorted() {
        List<Person> people = personRepository.findAllPeople();
        people.sort(null);
        return people;
    }

    public Person findPersonByName(String firstName, String lastName) {
        return personRepository.findPersonByName(firstName, lastName);
    }

    @Transactional
    public void deletePerson(Long id) {
        personRepository.deletePerson(id);
    }
}
