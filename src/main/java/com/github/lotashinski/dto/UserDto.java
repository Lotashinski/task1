package com.github.lotashinski.dto;

import com.github.lotashinski.entity.RolesEnum;

import java.util.List;

public final class UserDto {
    private Long id;
    private String login;
    private List<RolesEnum> roles;


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

    public List<RolesEnum> getRoles() {
        return roles;
    }

    public void setRoles(List<RolesEnum> roles) {
        this.roles = roles;
    }
}
