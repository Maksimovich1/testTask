package com.example.testTask.repo;

import com.example.testTask.domain.Client;

import java.util.List;

public interface ClientRepository {

    Client save(Client client);

    Client findById(Long id);

    List<Client> findByParams(String name, String surname, String secondName, String phone, String email);
}
