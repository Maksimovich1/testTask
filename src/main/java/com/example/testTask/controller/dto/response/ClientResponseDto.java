package com.example.testTask.controller.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ClientResponseDto {
    private Long id;
    private Long bankId;
    private String firstName;
    private String secondName;
    private String surname;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthday;
    private String passportNumber;
    private String birthPlace;
    private String phone;
    private String email;
    private String registrationAddress;
    private String residentialAddress;
}
