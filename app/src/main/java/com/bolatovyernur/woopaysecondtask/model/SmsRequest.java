package com.bolatovyernur.woopaysecondtask.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SmsRequest {
    @SerializedName("login")
    @Expose
    private String login;
    @SerializedName("activation_code")
    @Expose
    private String activationCode;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getActivationCode() {
        return activationCode;
    }

    public void setActivationCode(String activationCode) { this.activationCode = activationCode; }

}
