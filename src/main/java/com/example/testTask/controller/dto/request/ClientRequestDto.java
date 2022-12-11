package com.example.testTask.controller.dto.request;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ClientRequestDto {
    private Long bankId;
    private String firstName;
    private String secondName;
    private String surname;
    private LocalDate birthday;
    private String passportNumber;
    private String birthPlace;
    private String phone;
    private String email;
    private String registrationAddress;
    private String residentialAddress;
}
