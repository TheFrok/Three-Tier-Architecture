package com.example;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/persons")
@CrossOrigin(origins = "http://localhost:3000")
public class PersonResource {

    private final PersonService personService;

    public PersonResource(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping
    public ResponseEntity<Void> addPerson(@RequestBody Person person) {
        personService.addPerson(person);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<Person>> getAllPeople(@RequestParam(required = false) boolean sorted) {
        if (sorted) {
            return ResponseEntity.ok(personService.findAllPeopleSorted());
        }
        return ResponseEntity.ok(personService.findAllPeople());
    }

    @GetMapping("/search")
    public ResponseEntity<Person> findPersonByName(@RequestParam String firstName, @RequestParam String lastName) {
        Person person = personService.findPersonByName(firstName, lastName);
        if (person != null) {
            return ResponseEntity.ok(person);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deletePerson(@RequestParam String firstName, @RequestParam String lastName) {
        personService.deletePerson(firstName, lastName);
        return ResponseEntity.ok().build();
    }
}