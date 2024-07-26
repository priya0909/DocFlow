package com.example.DocFlow.Entity;

import com.example.DocFlow.Enums.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrgUserSR {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long serialNo;

    @Column
    private Long orgUserId;

    @Column
    private String title;


    @Column
    private String description;


    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "serialNo_fk")
    private List<User_Org_SR_File> user_org_sr_fileList;




}
