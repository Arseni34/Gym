package com.gym.model;

import com.gym.controller.main.Main;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Chat {
    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;

    private String text;
    private String dateAndTime = Main.getDateAndTime();

    @ManyToOne
    private Users owner;
    @ManyToOne
    private Purchased purchased;

    public Chat(String text, Users owner, Purchased purchased) {
        this.text = text;
        this.owner = owner;
        this.purchased = purchased;
    }
}
