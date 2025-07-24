package com.example;

import java.util.regex.Pattern;

public class PersonService {

    private static final Pattern EMAIL_PATTERN = Pattern.compile(
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" +
            "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");

    public boolean addPerson(Person person) {
        if (person.firstName() == null || person.firstName().isEmpty() ||
            person.lastName() == null || person.lastName().isEmpty() ||
            person.email() == null || person.email().isEmpty()) {
            throw new IllegalArgumentException("Missing required fields");
        }

        if (!EMAIL_PATTERN.matcher(person.email()).matches()) {
            throw new IllegalArgumentException("Invalid email format");
        }

        // In a real application, this would save the person to a database.
        // For now, we just return true.
        return true;
    }
}
