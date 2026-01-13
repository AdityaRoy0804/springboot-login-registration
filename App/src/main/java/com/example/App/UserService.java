package com.example.App;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final JdbcTemplate jdbcTemplate;

    public UserService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // Check if username already exists
    public boolean userExists(String username) {
        String sql = "SELECT COUNT(*) FROM users WHERE username = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, username);
        return count != null && count > 0;
    }

    // Save new user
    public void saveUser(String username, String password) {
        String sql = "INSERT INTO users (username, password) VALUES (?, ?)";
        jdbcTemplate.update(sql, username, password);
    }

    // Validate login
    public boolean validateLogin(String username, String password) {
        String sql = "SELECT COUNT(*) FROM users WHERE username = ? AND password = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, username, password);
        return count != null && count == 1;
    }
}