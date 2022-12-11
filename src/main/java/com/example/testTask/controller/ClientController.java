package com.example.testTask.controller;

import com.example.testTask.controller.dto.request.ClientRequestDto;
import com.example.testTask.controller.dto.response.ClientResponseDto;
import com.example.testTask.controller.mapper.ClientMapper;
import com.example.testTask.domain.Client;
import com.example.testTask.service.ClientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/v1/app/client")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService service;
    private final ClientMapper clientMapper;

    @PostMapping
    public ResponseEntity<ClientResponseDto> save(
            @RequestBody @Valid @NotNull ClientRequestDto clientRequestDto,
            @RequestHeader(name = "x-Source") String source) {
        log.info("!@@@!!!" + clientRequestDto.toString());
        Client client = service.save(clientMapper.mapToEntity(clientRequestDto), source);
        log.info("Client have been saved with id {}", client);
        return new ResponseEntity<>(clientMapper.mapToDto(client), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientResponseDto> findById(@PathVariable Long id) {
        return new ResponseEntity<>(clientMapper.mapToDto(service.getById(id)), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ClientResponseDto>> findByParam(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String surname,
            @RequestParam(required = false) String secondName,
            @RequestParam(required = false) String phone,
            @RequestParam(required = false) String email
    ) {
        return new ResponseEntity<>(service.getAllByParams(name, surname, secondName, phone, email)
                .stream()
                .map(clientMapper::mapToDto)
                .collect(Collectors.toList()), HttpStatus.OK);
    }
}
