package com.bolatovyernur.woopaysecondtask.registration;

public class RegisterRequest {
    private int phoneNum;
    private String login;
    private String password;

    public RegisterRequest(int phoneNum, String login, String password) {
        this.phoneNum = phoneNum;
        this.login = login;
        this.password = password;
    }

    public int getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(int phoneNum) {
        this.phoneNum = phoneNum;
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
}
