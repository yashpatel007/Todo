package com.todo.error;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
@ResponseStatus
public class RestExceptionHandeler extends ResponseEntityExceptionHandler {
    /* this will be executed when a controller throws exception of type Exception.class*/
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessage> taskNotFoundException(Exception exception, WebRequest request) {
        ErrorMessage message = ErrorMessage.builder().message(exception.getMessage()).code("ERROR").build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
    }
}
