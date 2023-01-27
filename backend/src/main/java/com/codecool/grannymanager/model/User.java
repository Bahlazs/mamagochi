package com.codecool.grannymanager.model;

import lombok.Getter;
import lombok.Setter;

@Getter
//@Setter
//@Entitty
public class User {

//    @Id
    private final int id;
    private final String name;
    private String password;
    private String email;

    public User(int id, String name, String password, String email) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.email = email;
    }


//    remove all of this
    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isId(int id) {
        return this.id == id;
    }

    public boolean isEmail(String email) {
        return this.email.equals(email);
    }
}
