package com.bolatovyernur.woopaysecondtask.model;

import java.util.List;

public class AuthResponse {
    private String parent_login;
    private int subject_type;
    private int country;
    private int id;
    private String login;
    private int status;
    private List<String> roles;
    private String assignments;
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    private String email;
    private String created_at;
    private int identified;
    private boolean resident_kz;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}