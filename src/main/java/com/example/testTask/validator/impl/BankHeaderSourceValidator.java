package com.example.testTask.validator.impl;

import com.example.testTask.domain.Client;
import com.example.testTask.exception.ValidationException;
import com.example.testTask.validator.HeaderSourceValidator;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class BankHeaderSourceValidator implements HeaderSourceValidator {
    @Override
    public void validation(Client client) {
        if (!StringUtils.hasText(client.getFirstName()) ||
                !StringUtils.hasText(client.getSecondName()) ||
                !StringUtils.hasText(client.getSurname()) ||
                !StringUtils.hasText(client.getPassportNumber()) ||
                client.getBankId() == null ||
                client.getBirthday() == null) {
            throw new ValidationException("FirstName, SecondName, Surname, PassportNumber, BankId, Birthday are mandatory!");
        }
    }

    @Override
    public String getType() {
        return "bank";
    }
}
