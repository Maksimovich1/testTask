package com.example.testTask.validator;

import com.example.testTask.domain.Client;

public interface HeaderSourceValidator {
    void validation(Client client);
    String getType();
}
