package com.prokopovich.usermanagementapp.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "user_accounts")
public class UserAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "role")
    private String role;
    @Column(name = "status")
    private String status;
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    public UserAccount() { }

    public UserAccount(int id, String username, String password, String firstName, String lastName,
                       String role, String status, LocalDateTime createdAt) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
        this.status = status;
        this.createdAt = createdAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserAccount that = (UserAccount) o;
        return id == that.id &&
                Objects.equals(username, that.username) &&
                Objects.equals(password, that.password) &&
                Objects.equals(firstName, that.firstName) &&
                Objects.equals(lastName, that.lastName) &&
                Objects.equals(role, that.role) &&
                Objects.equals(status, that.status) &&
                Objects.equals(createdAt, that.createdAt);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 37 * result + (username != null ? username.hashCode() : 0);
        result = 37 * result + (password != null ? password.hashCode() : 0);
        result = 37 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 37 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 37 * result + (role != null ? role.hashCode() : 0);
        result = 37 * result + (status != null ? status.hashCode() : 0);
        result = 37 * result + (createdAt != null ? createdAt.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "UserAccount: " +
                "id = " + id +
                ", username = " + username +
                ", password = " + password +
                ", firstName = " + firstName +
                ", lastName = " + lastName +
                ", role = " + role +
                ", status = " + status +
                ", createdAt = " + createdAt + ';';
    }
}