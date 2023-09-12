package com.codecool.grannymanager.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity(name = "app_user")
@Builder
@AllArgsConstructor
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
    @Column(unique = true)
    private  String userName;
    private String password;
    @Column(unique = true)
    private String email;
    @Enumerated(EnumType.STRING)
    private Role role;


    public String toString(){
        return String.format("Name: %s, id: %d", userName, id);
    }




}
