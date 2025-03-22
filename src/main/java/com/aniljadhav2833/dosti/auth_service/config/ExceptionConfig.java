package com.aniljadhav2833.dosti.auth_service.config;

import com.aniljadhav2833.dosti.auth_service.DTO.Response;
import com.aniljadhav2833.dosti.auth_service.Exceptions.CustomException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@Configuration
@ControllerAdvice
public class ExceptionConfig {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<Response> exception(CustomException e){
        return new ResponseEntity<>(new Response("Something is incorrect here", e.getMessage(), LocalDateTime.now()), HttpStatus.BAD_REQUEST);
    }

}
