package com.example.smartcoffeecourt.ApiService;

public class UserRegistrationRequest {
    private String email;
    private String password;
    private  String username;

    private String phone;

    public UserRegistrationRequest(String email, String password, String username, String phone) {
        this.email = email;
        this.password = password;
        this.username = username;
        this.phone = phone;
    }
    public UserRegistrationRequest(){}

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
// Constructors, getters, and setters
    // ...
}
