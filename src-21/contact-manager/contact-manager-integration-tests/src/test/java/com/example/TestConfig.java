package com.example;

import com.example.PersonResource;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Import;

@TestConfiguration
@Import(PersonResource.class)
public class TestConfig {
}
