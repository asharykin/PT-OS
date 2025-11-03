package ru.mipt.web.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({NoHandlerFoundException.class, NoResourceFoundException.class})
    public ResponseEntity<Void> handleNoHandlerOrResourceFoundException(Exception ex) {
        // чтобы убрать тело ошибочного ответа по умолчанию при запросе на несуществующий эндпоинт
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
