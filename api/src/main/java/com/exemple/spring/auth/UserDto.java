package com.exemple.spring.auth;

public class UserDto {
    private Long id;
    private String name;
    private String roles;
    private String mail;
    private String pass;

    public UserDto() {
    }

    public UserDto(Long id, String name, String roles, String mail, String pass) {
        this.id = id;
        this.name = name;
        this.roles = roles;
        this.mail = mail;
        this.pass = pass;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
