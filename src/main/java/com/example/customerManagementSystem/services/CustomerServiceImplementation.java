package com.example.customerManagementSystem.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.customerManagementSystem.entities.Customer;
import com.example.customerManagementSystem.exceptions.CustomerNotFoundException;
import com.example.customerManagementSystem.repositories.CustomerRepository;

@Service
public class CustomerServiceImplementation implements CustomerService {

	CustomerRepository customerRepository;

	@Autowired
	public CustomerServiceImplementation(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	@Override
	public Customer saveCustomer(Customer customer) {
		return customerRepository.save(customer);
	}

	@Override
	public List<Customer> getAllCustomers() {
		return customerRepository.findAll();
	}

	@Override
	public Customer findCustomer(long id) {
		return customerRepository.findById(id).orElseThrow(
				() -> new CustomerNotFoundException("Customer with the provided Id does not exist"));
	}

	@Override
	public long deleteCustomer(long id) {
		customerRepository.findById(id).orElseThrow(
				() -> new CustomerNotFoundException("Customer with the provided Id does not exist"));
		customerRepository.deleteById(id);
		return id;
	}

	@Override
	public Customer updateEmail(long id, String email) {
		Customer Updatedcustomer = customerRepository.findById(id).orElseThrow(
				() -> new CustomerNotFoundException("Customer with the provided Id does not exist"));
		Updatedcustomer.setEmailId(email);
		customerRepository.save(Updatedcustomer);
		return Updatedcustomer;
	}

	@Override
	public List<Customer> findAllCustomersWithLastName(String lastName) {
		return customerRepository.findAllByLastName(lastName);
	}

	@Override
	public Page<Customer> getAllCustomers(int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo-1,pageSize/*,sort*/);
		return customerRepository.findAll(pageable);
	}

}
