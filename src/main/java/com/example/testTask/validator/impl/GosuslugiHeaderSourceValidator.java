package com.example.testTask.validator.impl;

import com.example.testTask.domain.Client;
import com.example.testTask.exception.ValidationException;
import com.example.testTask.validator.HeaderSourceValidator;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class GosuslugiHeaderSourceValidator implements HeaderSourceValidator {
    @Override
    public void validation(Client client) {
        if (!StringUtils.hasText(client.getFirstName()) ||
                !StringUtils.hasText(client.getSecondName()) ||
                !StringUtils.hasText(client.getSurname()) ||
                !StringUtils.hasText(client.getPassportNumber()) ||
                !StringUtils.hasText(client.getRegistrationAddress()) ||
                !StringUtils.hasText(client.getPhone()) ||
                !StringUtils.hasText(client.getBirthPlace()) ||
                !StringUtils.hasText(client.getPassportNumber()) ||
                client.getBankId() == null ||
                client.getBirthday() == null) {
            throw new ValidationException("FirstName, SecondName, Surname, BirthPlace, RegistrationAddress, Phone, PassportNumber, BankId, Birthday are mandatory!");
        }
    }
    
    @Override
    public String getType() {
        return "gosuslugi";
    }
}
