package com.codecool.grannymanager.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "app_user")
@NoArgsConstructor
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "USER_ID", nullable = false)
    private Long id;
    @OneToOne
    @JoinColumn(name = "GRANNY_ID",referencedColumnName = "GRANNY_ID")
    @JsonIgnore
    private Granny granny;
    private  String name;
    private String password;
    private String email;

    public User( String name, String password, String email) {
        this.name = name;
        this.password = password;
        this.email = email;
    }

    public String toString(){
        return String.format("Name: %s, id: %d", name, id);
    }

}
