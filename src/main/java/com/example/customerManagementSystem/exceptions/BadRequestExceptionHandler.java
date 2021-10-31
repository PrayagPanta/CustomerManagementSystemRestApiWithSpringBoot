package com.example.customerManagementSystem.exceptions;

import java.util.HashMap;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
@RestController
public class BadRequestExceptionHandler {
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public final ResponseEntity<?> customerNotFoundExceptionHandler(MethodArgumentNotValidException exception) {
		HashMap<String, String> message = new HashMap<String, String>();
		  exception.getBindingResult().getAllErrors().forEach((error) -> {
		        String fieldName = ((FieldError) error).getField();
		        String errorMessage = error.getDefaultMessage();
		        message.put(fieldName, errorMessage);
		    });
		return new ResponseEntity<Object>(message, HttpStatus.BAD_REQUEST);
	}
}




