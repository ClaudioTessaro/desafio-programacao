package com.br.magalu.desafio.api.exception_handler;

import com.br.magalu.desafio.api.exceptions.GeneralException;
import com.br.magalu.desafio.api.exceptions.ResponseError;
import com.br.magalu.desafio.domain.util.MessageUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.OffsetDateTime;


@ControllerAdvice
@Slf4j
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    private final MessageUtil messageUtil;

    @ExceptionHandler(value = IllegalArgumentException.class)
    public ResponseEntity<ResponseError> validation(IllegalArgumentException e) {
        ResponseError err = new ResponseError(e.getMessage(), OffsetDateTime.now(), HttpStatus.BAD_REQUEST.value());
        log.error("Argumento invalido: {}", err);
        return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = GeneralException.class)
    public ResponseEntity<ResponseError> generalException(GeneralException e) {
        ResponseError err = new ResponseError(e.getMessage(), OffsetDateTime.now(), HttpStatus.INTERNAL_SERVER_ERROR.value());
        log.error("Error: {}", err);
        return new ResponseEntity<>(err, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}