package com.example.DocFlow.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "")
    public class Organisation {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long orgId;

        @Column(nullable = false)
        private String name;

        @Column(nullable = false)
        private String description;


       @OneToMany(targetEntity = User.class,cascade =CascadeType.ALL,fetch = FetchType.LAZY,orphanRemoval = true)
       List<User> users = new ArrayList<>();

    }


