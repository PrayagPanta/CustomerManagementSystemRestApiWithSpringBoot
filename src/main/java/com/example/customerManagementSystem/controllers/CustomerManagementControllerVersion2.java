package com.example.customerManagementSystem.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.customerManagementSystem.services.CustomerService;

@RestController
@RequestMapping("/api/v2/")
public class CustomerManagementControllerVersion2 {
	private CustomerService customerService;

	@Autowired
	public CustomerManagementControllerVersion2(CustomerService customerService) {
		this.customerService = customerService;
	}

	@GetMapping("/getAllCustomers/{pageNo}/{pageSize}")
	public ResponseEntity<?> getAllEmployees(@PathVariable(value = "pageNo") int pageNo,
			@PathVariable(value = "pageSize") int pageSize) {
		return ResponseEntity
				.ok(customerService.getAllCustomers(pageNo, pageSize/* sortField,sortDirection */).getContent());
	}
}
