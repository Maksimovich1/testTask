package com.example.testTask.controller.mapper;

import com.example.testTask.controller.dto.request.ClientRequestDto;
import com.example.testTask.controller.dto.response.ClientResponseDto;
import com.example.testTask.domain.Client;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ClientMapper {

    public final ModelMapper modelMapper;

    public Client mapToEntity(ClientRequestDto source) {
        return modelMapper.map(source, Client.class);
    }
    public ClientResponseDto mapToDto(Client source) {
        return modelMapper.map(source, ClientResponseDto.class);
    }
}
