package com.example.testTask.validator;

import com.example.testTask.domain.Client;
import com.example.testTask.domain.HeaderSourceType;

public interface HeaderSourceValidator {
    void validation(Client client);

    HeaderSourceType getType();
}
