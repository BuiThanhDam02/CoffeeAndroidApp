package vn.edu.hcmuaf.fit.coffeecourtrestfulapi.dto.request;

public class UserRegistrationRequest {
    private String email;
    private String password;
    private  String username;

    public UserRegistrationRequest(String email, String password, String username) {
        this.email = email;
        this.password = password;
        this.username = username;
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

    // Constructors, getters, and setters
    // ...
}
