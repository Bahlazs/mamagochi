package com.codecool.grannymanager.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
@Entity
@NoArgsConstructor
@Getter
@Setter
public class Granny {



    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "GRANNY_ID", nullable = false)
    private Long id;
    @OneToOne(mappedBy = "granny")
    @JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID")
    @JsonIgnore
    private User user;
    private String name;
    private LocalDateTime lastVisit;

    private  int environmentStat;
    private  int healthStat;
    private  int moodStat;

    private boolean retired;

    public Granny(User user, String name) {
        this.user = user;
        this.name = name;
        this.environmentStat = 3;
        this.healthStat = 3;
        this.moodStat = 3;
        this.retired = false;
        this.lastVisit = LocalDateTime.now();
    }

    public String toString(){
        return String.format("Name: %s , id: %d, userId: %d", name, id, user.getId());
    }
}
