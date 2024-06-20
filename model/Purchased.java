package com.gym.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Purchased {
    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Subs sub;
    @ManyToOne(fetch = FetchType.LAZY)
    private Users owner;
    @ManyToOne(fetch = FetchType.LAZY)
    private Trainers trainer;
    @OneToMany(mappedBy = "purchased", cascade = CascadeType.ALL)
    private List<Chat> chats = new ArrayList<>();

    public Purchased(Subs sub, Users owner, Trainers trainer) {
        this.sub = sub;
        this.owner = owner;
        this.trainer = trainer;
    }

    public List<Chat> getChats() {
        chats.sort(Comparator.comparing(Chat::getId));
        return chats;
    }
}
