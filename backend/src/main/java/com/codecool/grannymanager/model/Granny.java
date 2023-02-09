package com.codecool.grannymanager.model;

import com.codecool.grannymanager.model.enumgrannyproperties.Environment;
import com.codecool.grannymanager.model.enumgrannyproperties.Health;
import com.codecool.grannymanager.model.enumgrannyproperties.Mood;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Entity
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
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

    @Enumerated(EnumType.STRING)
    private Environment environmentStat;
    @Enumerated(EnumType.STRING)
    private Health healthStat;
    @Enumerated(EnumType.STRING)
    private Mood moodStat;
    private boolean retired;

    public Granny(User user, String name) {
        this.user = user;
        this.name = name;
        this.environmentStat = Environment.TIDY;
        this.healthStat = Health.HEALTHY;
        this.moodStat = Mood.HAPPY;
        this.retired = false;
        this.lastVisit = LocalDateTime.now();
    }

    public String toString(){
//        return String.format("Name: %s , id: %d, userId: %d", name, id, user.getId());
        return String.format("env: %s health: %s mood: %s retired: %s",environmentStat,healthStat,moodStat, retired);
    }
}
