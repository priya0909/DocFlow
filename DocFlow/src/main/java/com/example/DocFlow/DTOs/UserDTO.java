package com.example.DocFlow.DTOs;

import com.example.DocFlow.Enums.VerificationType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDTO {
    private String name;
    private String email;
    private String mobileNo;
    @Enumerated(EnumType.STRING)
    private VerificationType verificationType;

}
