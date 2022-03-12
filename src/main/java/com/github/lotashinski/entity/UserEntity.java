package com.github.lotashinski.entity;

import com.google.gson.annotations.Expose;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Basic
    @Column(nullable = false, length = 255)
    private String login;

    @Basic
    @Column(nullable = false, length = 255)
    private String password;


    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private List<RolesEnum> roles = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<CarSessionEntity> carSessions = new ArrayList<>();


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<RolesEnum> getRoles() {
        return roles;
    }

    public void setRoles(List<RolesEnum> roles) {
        this.roles = roles;
    }

    public void addRole(RolesEnum role) {
        if (!roles.contains(role)) {
            roles.add(role);
        }
    }

    public List<CarSessionEntity> getCarSessions() {
        return carSessions;
    }

    public void setCarSessions(List<CarSessionEntity> carSessions) {
        this.carSessions = carSessions;
    }
}
