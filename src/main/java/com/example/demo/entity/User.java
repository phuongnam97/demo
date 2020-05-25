package com.example.demo.entity;

import javax.persistence.*;

@Entity
@Table(name = "users", uniqueConstraints = {@UniqueConstraint(name = "users_uk", columnNames = "username")})            // unique key of table users
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private int id;

    @Column(name = "username", length = 36, nullable = false)
    private String username;

    @Column(name = "encryped_password", length = 128, nullable = false)
    private String encrypedPassword;

    @Column(name = "full_name", nullable = false)
    private String fullname;

    @Column(name = "status", nullable = false)
    private int status;

    public User(String username, String encrypedPassword, String fullname, int status) {
        this.username = username;
        this.encrypedPassword = encrypedPassword;
        this.fullname = fullname;
        this.status = status;
    }

    public User() {
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

    public String getEncrypedPassword() {
        return encrypedPassword;
    }

    public void setEncrypedPassword(String password) {
        this.encrypedPassword = password;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }
}
