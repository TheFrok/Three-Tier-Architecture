package com.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LoginServiceTest {

    @Test
    void login_shouldReturnTrue_whenCredentialsAreCorrect() {
        // Arrange
        LoginService loginService = new LoginService();

        // Act
        boolean result = loginService.login("user", "pass");

        // Assert
        assertTrue(result);
    }

    @Test
    void login_shouldReturnFalse_whenCredentialsAreIncorrect() {
        // Arrange
        LoginService loginService = new LoginService();

        // Act
        boolean result = loginService.login("user", "wrongpass");

        // Assert
        assertFalse(result);
    }
}
