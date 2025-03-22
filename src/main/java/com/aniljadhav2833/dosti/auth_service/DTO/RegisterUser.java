package com.aniljadhav2833.dosti.auth_service.DTO;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterUser {
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;          // Date of Birth
    private String gender;
    private String mobileNumber;
    private String emailId;
    private String address;
    private String pinCode;
    private String profileImage;
    private String password;
}
