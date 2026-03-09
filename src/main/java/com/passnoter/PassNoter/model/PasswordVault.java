package com.passnoter.PassNoter.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
public class PasswordVault {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String websiteName;
    private String username;
    private String email ;
    private String password;
    @ManyToOne
    @JoinColumn(name="user_id")
    @JsonIgnore
    private UserAuth userAuth;
    public PasswordVault(){}

    public PasswordVault(Long id, String websiteName, String username, String email, String password, UserAuth userAuth) {
        this.id = id;
        this.websiteName = websiteName;
        this.username = username;
        this.email = email;
        this.password = password;
        this.userAuth = userAuth;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWebsiteName() {
        return websiteName;
    }

    public void setWebsiteName(String websiteName) {
        this.websiteName = websiteName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

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

    public UserAuth getUserAuth() {
        return userAuth;
    }

    public void setUserAuth(UserAuth userAuth) {
        this.userAuth = userAuth;
    }
}
