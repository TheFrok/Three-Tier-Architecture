package com.example;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "person")
public class PersonEntity {

    @Id
    @GeneratedValue
    private Long id;

    private String firstName;
    private String lastName;
    private String dateOfBirth;
    private String age;
    private String email;
    private String phoneNumber;
    private String address1;
    private String address2;
    private String city;
    private String zipCode;
    private String state;
    private String country;

    public Person toPerson() {
        return new Person(firstName, lastName, dateOfBirth, age, email, phoneNumber, address1, address2, city, zipCode, state, country);
    }

    public static PersonEntity fromPerson(Person person) {
        PersonEntity entity = new PersonEntity();
        entity.setFirstName(person.firstName());
        entity.setLastName(person.lastName());
        entity.setDateOfBirth(person.dateOfBirth());
        entity.setAge(person.age());
        entity.setEmail(person.email());
        entity.setPhoneNumber(person.phoneNumber());
        entity.setAddress1(person.address1());
        entity.setAddress2(person.address2());
        entity.setCity(person.city());
        entity.setZipCode(person.zipCode());
        entity.setState(person.state());
        entity.setCountry(person.country());
        return entity;
    }

    // Getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}