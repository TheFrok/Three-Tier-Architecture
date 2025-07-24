package com.example;

public class LoginService {
    public boolean login(String username, String password) {
        return "user".equals(username) && "pass".equals(password);
    }
}
