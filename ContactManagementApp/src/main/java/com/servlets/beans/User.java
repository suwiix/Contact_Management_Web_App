package com.servlets.beans;

public class User {
    private int userId;
    private String userName;
    private String emailId;
    private String pass;

    public User(String emailId, String pass){
        this.emailId = emailId;
        this.pass = pass;
    }


    public User(int userId, String userName, String emailId, String pass) {
        this.userId = userId;
        this.userName = userName;
        this.emailId = emailId;
        this.pass = pass;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
