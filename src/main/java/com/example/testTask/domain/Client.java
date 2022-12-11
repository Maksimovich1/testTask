package com.example.testTask.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "client")
@ToString
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "bank_id")
    private Long bankId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "second_name")
    private String secondName;

    @Column(name = "surname")
    private String surname;

    @Column(name = "birthday")
    @Past
    private LocalDate birthday;

    @Column(name = "passport_number")
    @Pattern(regexp = "[A-Z]{2}\\d{7}", message = "Check pls passportNumber")
    private String passportNumber;

    @Column(name = "birth_place")
    private String birthPlace;

    @Column(name = "phone")
    @Pattern(regexp = "[+]\\d{12}", message = "Check phone pls")
    private String phone;

    @Column(name = "email")
    @Email
    private String email;

    @Column(name = "registration_address")
    private String registrationAddress;

    @Column(name = "residential_address")
    private String residentialAddress;

}
