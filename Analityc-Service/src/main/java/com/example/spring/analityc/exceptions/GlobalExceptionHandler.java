package com.example.spring.analityc.exceptions;


import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpServerErrorException;

import java.net.ConnectException;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler
    public void catchInternalServerError(HttpServerErrorException.InternalServerError e) {
        log.error(e.getMessage(), e);
        System.out.println("Сервис Аналитики недоступен");
//        return new ResponseEntity<>(new CartServiceIntegrationException("Сервис не работает"), HttpStatus.SERVICE_UNAVAILABLE);
    }

    @ExceptionHandler
    public void catchConnectException(ConnectException e) {
        log.error(e.getMessage(), e);
        System.out.println("БД Сервиса Аналитики недоступна");
//        return new ResponseEntity<>(new CartServiceIntegrationException("Сервис не работает"), HttpStatus.SERVICE_UNAVAILABLE);
    }
}
