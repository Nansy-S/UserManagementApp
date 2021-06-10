package com.prokopovich.usermanagementapp.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class UserAccountDto {

    @NotBlank(message="The field is not filled.")
    @Pattern(regexp="[a-zA-Z]{3,}", message="username должно содержать только латинские буквы")
    private String username;

    @NotBlank(message="The field is not filled.")
    @Pattern(regexp="[a-zA-Z0-9]{3,}", message="password должно содержать только латинские буквы и цифры")
    @Pattern(regexp="[a-zA-Z]", message="password должен содержать хоть 1 букву")
    @Pattern(regexp="[0-9]", message="password должен содержать хоть 1 цифру")
    private String password;

    @NotBlank(message="The field is not filled.")
    @Pattern(regexp="[a-zA-Z]", message="firstName должно содержать только латинские буквы")
    private String firstName;

    @NotBlank(message="The field is not filled.")
    @Pattern(regexp="[a-zA-Z]", message="lastName должно содержать только латинские буквы")
    private String lastName;

    private String role;

    private String status;

    public UserAccountDto() { }

    public UserAccountDto(String username, String password, String firstName, String lastName,
                          String role, String status) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
        this.status = status;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
