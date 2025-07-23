# Database Schema

This document outlines the database schema for the application. The application uses a single table to store person information.

## Table: `PERSON`

The `PERSON` table stores all the information about an individual.

| Column Name   | Data Type | Constraints | Description                               |
|---------------|-----------|-------------|-------------------------------------------|
| `FIRSTNAME`   | `TEXT`    | `NOT NULL`  | The first name of the person.             |
| `LASTNAME`    | `TEXT`    | `NOT NULL`  | The last name of the person.              |
| `DATEOFBIRTH` | `TEXT`    | `NOT NULL`  | The person's date of birth.               |
| `AGE`         | `TEXT`    | `NOT NULL`  | The person's age.                         |
| `EMAIL`       | `TEXT`    | `NOT NULL`  | The person's email address.               |
| `PHONENUMBER` | `TEXT`    | `NOT NULL`  | The person's phone number.                |
| `ADDRESS1`    | `TEXT`    | `NOT NULL`  | The first line of the person's address.   |
| `ADDRESS2`    | `TEXT`    |             | The second line of the person's address.  |
| `CITY`        | `TEXT`    | `NOT NULL`  | The city of the person's address.         |
| `ZIP`         | `TEXT`    | `NOT NULL`  | The zip code of the person's address.     |
| `STATE`       | `TEXT`    | `NOT NULL`  | The state of the person's address.        |
| `COUNTRY`     | `TEXT`    | `NOT NULL`  | The country of the person's address.      |
