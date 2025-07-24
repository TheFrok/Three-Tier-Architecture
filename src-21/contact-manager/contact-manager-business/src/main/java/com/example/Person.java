package com.example;

public record Person(
    String firstName,
    String lastName,
    String dateOfBirth,
    String age,
    String email,
    String phoneNumber,
    String address1,
    String address2,
    String city,
    String zipCode,
    String state,
    String country
) {}
