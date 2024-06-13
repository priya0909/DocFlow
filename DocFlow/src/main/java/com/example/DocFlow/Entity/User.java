package com.example.DocFlow.Entity;


import com.example.DocFlow.Enums.VerificationType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class   User {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long userId;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, unique = true)
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

    @Enumerated(EnumType.STRING)
    private VerificationType verificationType;

    @ManyToOne
    Organisation organisation;

    @OneToMany(targetEntity = Organisation.class, cascade =CascadeType.ALL,fetch = FetchType.LAZY,orphanRemoval = true)
     List<Organisation> organisations;


}



