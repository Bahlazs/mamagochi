package com.codecool.grannymanager.model;

import lombok.Getter;

@Getter
public class UserLogin {

    private String password;
    private String email;

    public UserLogin(String password, String email) {
        this.password = password;
        this.email = email;
    }
}
