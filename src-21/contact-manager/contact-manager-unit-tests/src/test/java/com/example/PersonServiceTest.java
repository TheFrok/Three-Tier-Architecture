package com.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PersonServiceTest {

    @Test
    void addPerson_shouldAddPerson_whenPersonIsValid() {
        // Arrange
        PersonService personService = new PersonService();
        Person person = new Person("John", "Doe", "2000-01-01", "25", "john.doe@example.com", "1234567890", "123 Main St", "", "Anytown", "12345", "CA", "USA");

        // Act
        boolean result = personService.addPerson(person);

        // Assert
        assertTrue(result);
    }

    @Test
    void addPerson_shouldThrowException_whenPersonIsMissingRequiredFields() {
        // Arrange
        PersonService personService = new PersonService();
        Person person = new Person("", "", "", "", "", "", "", "", "", "", "", "");

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            personService.addPerson(person);
        });
    }

    @Test
    void addPerson_shouldThrowException_whenEmailIsInvalid() {
        // Arrange
        PersonService personService = new PersonService();
        Person person = new Person("John", "Doe", "2000-01-01", "25", "invalid-email", "1234567890", "123 Main St", "", "Anytown", "12345", "CA", "USA");

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            personService.addPerson(person);
        });
    }
}
