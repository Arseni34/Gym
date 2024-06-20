package com.gym.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Groups {
    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;

    private String dateAndTime;
    private String name;
    private String photo;

    @ManyToOne(fetch = FetchType.LAZY)
    private Trainers trainer;

    public Groups(String dateAndTime, String name, String photo, Trainers trainer) {
        this.dateAndTime = dateAndTime;
        this.name = name;
        this.photo = photo;
        this.trainer = trainer;
    }

    public String getDateAndTime() {
        String[] res = dateAndTime.split("T");
        return res[0] + ' ' + res[1];
    }
}
