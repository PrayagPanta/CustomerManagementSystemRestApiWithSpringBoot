package com.example.customerManagementSystem.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.customerManagementSystem.entities.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
	public List<Customer> findAllByLastName(String lastName);
}
