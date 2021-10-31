package com.example.customerManagementSystem;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.List;

import javax.validation.ConstraintViolationException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.example.customerManagementSystem.entities.Customer;
import com.example.customerManagementSystem.repositories.CustomerRepository;
import com.example.customerManagementSystem.services.CustomerService;
import com.example.customerManagementSystem.services.CustomerServiceImplementation;

@ExtendWith(MockitoExtension.class)
public class CustomerManagementSystemServicesTest {

	@Mock
	private CustomerRepository customerRepository;

	private CustomerService customerService;
	
	Customer customer = new Customer(1, "Prayag", "Panta", "prayagpanta@hotmail.com");
	Customer customer2 = new Customer(2, "Prayag2", "Panta2", "prayagpanta2@hotmail.com");

	@BeforeEach
	void initUseCase() {
		customerService = new CustomerServiceImplementation(customerRepository);
	}

	@Test
	public void savedCustomer_Success() {
		Customer customer = new Customer(1, "Prayag", "Panta", "prayagpanta@hotmail.com");
		when(customerRepository.save(customer)).thenReturn(customer);
		Customer savedCustomer = customerService.saveCustomer(customer);
		assertTrue(savedCustomer.getFirstName().equals("Prayag"));
	}
	
	@Test
	public void savedCustomer_Fail() {
		Customer customer = new Customer(1, "Prayag", "Panta", "prayagpanta@hotmail.com333333333333333333333333333333333333333333333333333333");
		when(customerRepository.save(customer))
		.thenThrow(new ConstraintViolationException("Email size should not be longer than 80 characters", null));
        try {
		customerService.saveCustomer(customer);
        }
        catch(Exception e)
        {
        	assertTrue((e.getLocalizedMessage()).equals("Email size should not be longer than 80 characters"));
        }
	}
	
	@Test
	public void getAllCustomers() {
		when(customerRepository.findAll()).thenReturn(List.of(customer,customer2));
		List<Customer> customerList = customerRepository.findAll();
		assertTrue(customerList.size()==2);
		assertTrue(customerList.get(0).equals(customer));
	}
	
	//Testing Paging
	@Test
	public void getPagedCustomers() {
		Pageable pageable = PageRequest.of(0,1);
		Page<Customer> customerPage = new PageImpl<>(List.of(customer,customer2),pageable,1);
		when(customerRepository.findAll(pageable)).thenReturn(customerPage);
		assertEquals(customerRepository.findAll(pageable),customerPage);
	}
}
