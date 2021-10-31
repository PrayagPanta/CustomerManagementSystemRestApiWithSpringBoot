package com.example.customerManagementSystem.exceptions;

import java.util.HashMap;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ConstraintViolationExceptionHandler {

	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<?> handleConstraintViolationException(ConstraintViolationException exception) {
		HashMap<String, String> message = new HashMap<String, String>();
		message.put("Http Status Code","400");
		message.put("Error",exception.getLocalizedMessage());
		return new ResponseEntity<Object>(message, HttpStatus.BAD_REQUEST);
	}

}
