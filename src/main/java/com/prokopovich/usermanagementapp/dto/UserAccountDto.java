package com.prokopovich.usermanagementapp.dto;

import com.prokopovich.usermanagementapp.validator.UniqueUsername;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class UserAccountDto {

    @NotBlank(message="The field is not filled.")
    @Size(min=3, max=16, message="Username must be at least 3 and no more than 16 characters.")
    @Pattern(regexp="^[a-zA-Z]+$", message="Username must contain only Latin letters")
    @UniqueUsername
    private String username;

    @NotBlank(message="The field is not filled.")
    @Size(min=3, max=16, message="Password must be at least 3 and no more than 16 characters.")
    @Pattern(regexp="^[a-zA-Z]+[0-9]+$", message="Password must contain only Latin letters or numbers, " +
                                                    "contain at least one character and at least one number")
    private String password;

    @NotBlank(message="The field is not filled.")
    @Size(min=1, max=16, message="First Name must be at least 3 and no more than 16 characters.")
    @Pattern(regexp="^[a-zA-Z]+$", message="First Name must contain only Latin letters")
    private String firstName;

    @NotBlank(message="The field is not filled.")
    @Size(min=1, max=16, message="Last Name must be at least 3 and no more than 16 characters.")
    @Pattern(regexp="^[a-zA-Z]+$", message="Last Name must contain only Latin letters")
    private String lastName;

    @NotBlank(message="The field is not filled.")
    private String role;

    @NotBlank(message="The field is not filled.")
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
