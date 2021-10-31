package com.example.customerManagementSystem.exceptions;

import java.util.HashMap;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class CustomerNotFoundExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(CustomerNotFoundException.class)
	public final ResponseEntity<?> customerNotFoundExceptionHandler(CustomerNotFoundException exception) {
		HashMap<String, String> message = new HashMap<String, String>();
		message.put("Http Status Code", String.valueOf(HttpStatus.NOT_FOUND.value()));
		message.put("HttpStatus", HttpStatus.NOT_FOUND.getReasonPhrase());
		message.put("Reason", exception.getMessage());
		return new ResponseEntity<Object>(message, HttpStatus.NOT_FOUND);
	}

}
