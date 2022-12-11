package com.example.testTask.validator;

import com.example.testTask.exception.ValidationException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class ClientSearchParamValidator {

    public void validation(String name, String surname, String secondName, String phone, String email) {
        if (!StringUtils.hasText(name) &&
                !StringUtils.hasText(surname) &&
                !StringUtils.hasText(secondName) &&
                !StringUtils.hasText(phone) &&
                !StringUtils.hasText(email)) {
            throw new ValidationException("At least one parameter should be not null!");
        }
    }

}
