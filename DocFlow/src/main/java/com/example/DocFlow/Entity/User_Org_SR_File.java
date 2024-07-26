package com.example.DocFlow.Entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

@RequiredArgsConstructor
@Data
@Entity
public class User_Org_SR_File {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String orgUserSRFilesId;

    @Column
    String type;

    @Column
    private String name;

    @Column
    private boolean isEncrypted;

    @Column
    private String password;

    @Lob
    private byte[] data;

    public User_Org_SR_File (String contentType, String name, boolean isEncrypted, byte[] data) {
        this.type = contentType;
        this.name = name;
        this.isEncrypted = isEncrypted;
        this.data = data;
    }

}
