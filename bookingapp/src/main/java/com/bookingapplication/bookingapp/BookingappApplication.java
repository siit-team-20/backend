package com.bookingapplication.bookingapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.bookingapplication.bookingapp.dtos.ErrorDTO;

@ControllerAdvice
@SpringBootApplication
@RestController
public class BookingappApplication extends ResponseEntityExceptionHandler {

	public static void main(String[] args) {
		SpringApplication.run(BookingappApplication.class, args);
	}
	
	@ExceptionHandler(AccessDeniedException.class)
    public final ResponseEntity<?> handleAccessDeniedException(Exception ex, WebRequest request) {
        return new ResponseEntity<ErrorDTO>(new ErrorDTO("Access denied!"), HttpStatus.FORBIDDEN);
    }

}
