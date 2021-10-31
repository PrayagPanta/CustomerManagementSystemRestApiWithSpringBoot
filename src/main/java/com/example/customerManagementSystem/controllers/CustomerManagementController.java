package com.example.customerManagementSystem.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.customerManagementSystem.entities.Customer;
import com.example.customerManagementSystem.services.CustomerService;

@RestController
@RequestMapping("/api/v1/")
public class CustomerManagementController {
	private CustomerService customerService;

	@Autowired
	CustomerManagementController(CustomerService customerService) {
		this.customerService = customerService;
	}

	@GetMapping("/")
	public ResponseEntity<?> showAllMethods() {
		return ResponseEntity.ok("Welcome to CustomerManagementSystemAPI");
	}

	@GetMapping("/getAllCustomers")
	public ResponseEntity<?> getAllCustomers() {
		List<Customer> customers = customerService.getAllCustomers();
		return ResponseEntity.ok(customers);
	}

	@PostMapping("/addCustomer")
	public ResponseEntity<?> addCustomer(@Valid @RequestBody Customer customer) {
		return new ResponseEntity<Customer>(customerService.saveCustomer(customer), HttpStatus.CREATED);
	}

	@GetMapping("/findCustomerById/{id}")
	public ResponseEntity<Customer> findCustomerById(@PathVariable long id) {
		return new ResponseEntity<Customer>(customerService.findCustomer(id), HttpStatus.OK);
	}

	@PatchMapping("/updateEmailId/{id}")
	public ResponseEntity<Customer> updateEmailId(@PathVariable long id, @RequestBody String email) {
		return new ResponseEntity<Customer>(customerService.updateEmail(id,email), HttpStatus.OK);
	}

	@DeleteMapping("/deleteCustomerById/{id}")
	public ResponseEntity<String> deleteCustomer(@PathVariable long id) {
		long idValue=customerService.deleteCustomer(id);
		return new ResponseEntity<String>("Successfully Deleted Customer with ID:"+idValue, HttpStatus.OK);
	}

	@GetMapping("findCustomersUsingLastName/{lastName}")
	public ResponseEntity<?> findCustomersUsingLastName(@PathVariable String lastName) {
		List<Customer> customers = customerService.findAllCustomersWithLastName(lastName);
		return ResponseEntity.ok(customers);
	}
}
