package com.stefanini.taskmanager.exceptions;

import com.stefanini.taskmanager.dtos.GenericResponseDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class CustomExceptionHandler  extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = { BadRequestException.class})
    protected ResponseEntity<Object> handleBadRequests(
            RuntimeException ex, WebRequest request) {
        GenericResponseDTO genericResponseDTO = GenericResponseDTO.builder()
                .message(ex.getMessage())
                .build();
        return handleExceptionInternal(ex, genericResponseDTO,
                new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(value = {NotFoundException.class})
    protected ResponseEntity<Object> handleNotFound(
            RuntimeException ex, WebRequest request) {
        GenericResponseDTO genericResponseDTO = GenericResponseDTO.builder()
                .message(ex.getMessage())
                .build();
        return handleExceptionInternal(ex, genericResponseDTO,
                new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(value = {InternalServerErrorException.class})
    protected ResponseEntity<Object> handleInternalServerError(
            RuntimeException ex, WebRequest request) {
        GenericResponseDTO genericResponseDTO = GenericResponseDTO.builder()
                .message(ex.getMessage())
                .build();
        return handleExceptionInternal(ex, genericResponseDTO,
                new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }
}
