package com.example.testTask.repo;

import com.example.testTask.domain.Client;
import com.example.testTask.exception.ClientNotFoundException;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Map;

@Slf4j
@Repository
@RequiredArgsConstructor
public class ClientRepositoryAdapter implements ClientRepository {

    private final ClientJpaRepository clientJpaRepository;
    private final EntityManager entityManager;

    @Override
    public Client save(@NonNull Client client) {
        log.debug("Save client {}", client);
        return clientJpaRepository.save(client);
    }

    @Override
    public Client findById(@NonNull Long id) {
        log.debug("Find client by id = {}", id);
        return clientJpaRepository.findById(id)
                .orElseThrow(() -> new ClientNotFoundException(String.format("Client with id %d not found", id)));
    }

    @Override
    public List<Client> findByParams(String name, String surname, String secondName, String phone, String email) {
        return clientJpaRepository.findAllClientsByParam(name, surname, secondName, phone, email);
    }
}
