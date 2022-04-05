package com.bolatovyernur.woopaysecondtask.model;

public class SmsRequest {
    private String login;
    private String activation_code;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getActivation_code() {
        return activation_code;
    }

    public void setActivation_code(String activation_code) { this.activation_code = activation_code; }

}
