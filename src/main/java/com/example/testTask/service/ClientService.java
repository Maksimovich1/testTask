package com.example.testTask.service;

import com.example.testTask.domain.Client;

import java.util.List;

public interface ClientService {
    Client save(Client client, String saveConstraintType);

    Client getById(Long id);

    List<Client> getAllByParams(String name, String surname, String secondName, String phone, String email);
}
