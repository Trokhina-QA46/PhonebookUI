package com.phonebook.models;

public class User  {
   private String mail;
   private String password;

    public User setMail(String mail) {
        this.mail = mail;
        return this;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getMail() {
        return mail;
    }

    public String getPassword() {
        return password;
    }
}