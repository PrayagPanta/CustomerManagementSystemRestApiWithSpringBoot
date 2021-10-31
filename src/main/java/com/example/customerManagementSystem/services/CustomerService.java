package com.example.customerManagementSystem.services;

import java.util.List;

import org.springframework.data.domain.Page;

import com.example.customerManagementSystem.entities.Customer;

public interface CustomerService {
	public Customer saveCustomer(Customer customer);

	public List<Customer> getAllCustomers();

	public Customer findCustomer(long id);

	public long deleteCustomer(long id);

	public List<Customer> findAllCustomersWithLastName(String lastName);

	public Customer updateEmail(long id, String email);

	Page<Customer> getAllCustomers(int pageNo, int pageSize);
}
