package com.inventory.management.system.Dto;

public class UserRequestDto {
    private String email;
    private String password;
    private String role; // optional, e.g., ROLE_CUSTOMER

    public UserRequestDto() {}

    public UserRequestDto(String email, String password, String role) {
        this.email = email;
        this.password = password;
        this.role = role;
    }

    // Getters & setters
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
}