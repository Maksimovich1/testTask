package com.example.testTask.validator.impl;

import com.example.testTask.domain.Client;
import com.example.testTask.exception.ValidationException;
import com.example.testTask.validator.HeaderSourceValidator;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class MailHeaderSourceValidator implements HeaderSourceValidator {
    @Override
    public void validation(Client client) {
        if (!StringUtils.hasText(client.getEmail()) || !StringUtils.hasText(client.getFirstName())) {
            throw new ValidationException("Check pls email and first name!");
        }
    }

    @Override
    public String getType() {
        return "mail";
    }
}
