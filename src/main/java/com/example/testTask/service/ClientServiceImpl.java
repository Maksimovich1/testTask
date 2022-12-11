package com.example.testTask.service;

import com.example.testTask.domain.Client;
import com.example.testTask.repo.ClientRepository;
import com.example.testTask.validator.ClientSearchParamValidator;
import com.example.testTask.validator.ValidationFactory;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ValidationFactory validationFactory;
    private final ClientRepository clientRepository;
    private final ClientSearchParamValidator clientSearchParamValidator;

    @Override
    public Client save(@NonNull Client client, String saveConstraintType) {
        log.info("Start saving client {}", client);
        validationFactory.getValidator(saveConstraintType).validation(client);
        log.debug("Validation was successful");
        return clientRepository.save(client);
    }

    @Override
    public Client getById(@NonNull Long id) {
        return clientRepository.findById(id);
    }

    @Override
    public List<Client> getAllByParams(String name, String surname, String secondName, String phone, String email) {
        clientSearchParamValidator.validation(name, surname, secondName, phone, email);
        log.debug("Validation of params was successful");
        return clientRepository.findByParams(name, surname, secondName, phone, email);
    }
}
