package com.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

class PersonServiceTest {

    @Test
    void addPerson_shouldAddPerson_whenPersonIsValid() {
        // Arrange
        PersonRepository personRepository = mock(PersonRepository.class);
        PersonService personService = new PersonService(personRepository);
        Person person = new Person("John", "Doe", "2000-01-01", "25", "john.doe@example.com", "1234567890", "123 Main St", "", "Anytown", "12345", "CA", "USA");

        // Act
        personService.addPerson(person);

        // Assert
        verify(personRepository).addPerson(person);
    }

    @Test
    void addPerson_shouldThrowException_whenPersonIsMissingRequiredFields() {
        // Arrange
        PersonService personService = new PersonService(null);
        Person person = new Person("", "", "", "", "", "", "", "", "", "", "", "");

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            personService.addPerson(person);
        });
    }

    @Test
    void addPerson_shouldThrowException_whenEmailIsInvalid() {
        // Arrange
        PersonService personService = new PersonService(null);
        Person person = new Person("John", "Doe", "2000-01-01", "25", "invalid-email", "1234567890", "123 Main St", "", "Anytown", "12345", "CA", "USA");

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            personService.addPerson(person);
        });
    }

    @Test
    void findAllPeople_shouldReturnAllPeople() {
        // Arrange
        PersonRepository personRepository = mock(PersonRepository.class);
        PersonService personService = new PersonService(personRepository);
        List<Person> expectedPeople = Arrays.asList(
            new Person("John", "Doe", "2000-01-01", "25", "john.doe@example.com", "1234567890", "123 Main St", "", "Anytown", "12345", "CA", "USA"),
            new Person("Jane", "Doe", "2002-02-02", "23", "jane.doe@example.com", "0987654321", "456 Oak Ave", "", "Anytown", "12345", "CA", "USA")
        );
        when(personRepository.findAllPeople()).thenReturn(expectedPeople);

        // Act
        List<Person> actualPeople = personService.findAllPeople();

        // Assert
        assertEquals(expectedPeople, actualPeople);
    }

    @Test
    void findAllPeople_shouldReturnAllPeopleSorted() {
        // Arrange
        PersonRepository personRepository = mock(PersonRepository.class);
        PersonService personService = new PersonService(personRepository);
        Person person1 = new Person("John", "Doe", "2000-01-01", "25", "john.doe@example.com", "1234567890", "123 Main St", "", "Anytown", "12345", "CA", "USA");
        Person person2 = new Person("Jane", "Doe", "2002-02-02", "23", "jane.doe@example.com", "0987654321", "456 Oak Ave", "", "Anytown", "12345", "CA", "USA");
        Person person3 = new Person("Adam", "Smith", "1990-03-03", "35", "adam.smith@example.com", "5555555555", "789 Pine St", "", "Othertown", "54321", "NY", "USA");

        List<Person> unsortedPeople = Arrays.asList(person1, person2, person3);
        List<Person> sortedPeople = Arrays.asList(person3, person2, person1);

        when(personRepository.findAllPeople()).thenReturn(unsortedPeople);

        // Act
        List<Person> actualPeople = personService.findAllPeopleSorted();

        // Assert
        assertEquals(sortedPeople, actualPeople);
    }
}