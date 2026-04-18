package com.coursework.entity;

import java.sql.Timestamp;

/**
 * User entity — represents a system user.
 * Maps to the `users` table in the database.
 */
public class User {

    private int userId;
    private String name;
    private String email;
    private String password;
    private String phone;
    private String role;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public User(int userId, String name, String email, String password, String phone, String role, Timestamp createdAt, Timestamp updatedAt) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.role = role;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public int getUserId() { return userId; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public String getPhone() { return phone; }
    public String getRole() { return role; }
    public Timestamp getCreatedAt() { return createdAt; }
    public Timestamp getUpdatedAt() { return updatedAt; }

    public void setUserId(int userId) { this.userId = userId; }
    public void setName(String name) { this.name = name; }
    public void setEmail(String email) { this.email = email; }
    public void setPassword(String password) { this.password = password; }
    public void setPhone(String phone) { this.phone = phone; }
    public void setRole(String role) { this.role = role; }

    @Override
    public String toString() {
        return "[" + userId + "] " + name + " (" + email + ")";
    }
}