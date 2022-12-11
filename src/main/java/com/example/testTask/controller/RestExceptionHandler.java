package com.example.testTask.controller;

import com.example.testTask.controller.dto.response.ErrorDto;
import com.example.testTask.exception.ClientNotFoundException;
import com.example.testTask.exception.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.stream.Collectors;

@Slf4j
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ErrorDto> handleGenericException(Exception e, WebRequest request) {
        log.error("Error processing " + request, e);
        ErrorDto errorDto = ErrorDto.builder()
                .message("Internal server error")
                .build();
        return new ResponseEntity<>(errorDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ClientNotFoundException.class)
    protected ResponseEntity<ErrorDto> notFoundException(ClientNotFoundException e, WebRequest request) {
        log.error("Error processing " + request, e);
        ErrorDto errorDto = ErrorDto.builder()
                .message(e.getMessage())
                .build();
        return new ResponseEntity<>(errorDto, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({ValidationException.class})
    protected ResponseEntity<ErrorDto> validationException(ValidationException e, WebRequest request) {
        log.error("Error processing " + request, e);
        ErrorDto errorDto = ErrorDto.builder()
                .message(e.getMessage())
                .build();
        return new ResponseEntity<>(errorDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({ConstraintViolationException.class})
    protected ResponseEntity<ErrorDto> validationJxException(ConstraintViolationException e, WebRequest request) {
        log.error("Error processing " + request, e);
        String message = e.getConstraintViolations().stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.joining(","));
        ErrorDto errorDto = ErrorDto.builder()
                .message(message)
                .build();
        return new ResponseEntity<>(errorDto, HttpStatus.BAD_REQUEST);
    }
}
