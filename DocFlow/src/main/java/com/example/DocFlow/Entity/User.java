package com.example.DocFlow.Entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column
    private String mobileNo;

    @Column
    private Date loginDate;

    @Column
    private Date CreatedDate;

    @Column
    private String CreatedBy;

    @Column
    private Date updatedDate;

    @Column
    private String updatedBy;

    @Column
    private Date emailVerifiedOn;

    @Column
    private Date phoneVerifiedOn;

    @ManyToOne
    Organisation organisation;

}



