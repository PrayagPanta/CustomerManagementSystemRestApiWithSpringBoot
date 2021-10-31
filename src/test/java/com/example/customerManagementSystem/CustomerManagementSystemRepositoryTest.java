package com.example.customerManagementSystem;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.customerManagementSystem.entities.Customer;
import com.example.customerManagementSystem.repositories.CustomerRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CustomerManagementSystemRepositoryTest {

	/*@Autowired
	private TestEntityManager entityManager;*/
	
	@Autowired
	private CustomerRepository customerRepository;
	
	
	@Test
	public void addCustomer()
	{
		Customer customer = new Customer();
		customer.setFirstName("Prayag");
		customer.setLastName("Panta");
		customer.setEmailId("prayagpanta@hotmail.com");
	    customerRepository.save(customer);
		
		Optional<Customer> foundCustomer = customerRepository.findById(customer.getId());
		assertEquals(foundCustomer.get(),customer);
	}
	
	@Test
	public void getCustomerByLastName()
	{
		//To persist multiple values at the same time use EntityManager and Transactional or create .sql files
		//Or use UserRepository.save??
		Customer customer = new Customer();
		customer.setFirstName("Prayag");
		customer.setLastName("Panta");
		customer.setEmailId("prayagpanta@hotmail.com");
	    customerRepository.save(customer);
		List<Customer> foundCustomer = customerRepository.findAllByLastName("Panta");
		assertEquals(foundCustomer.get(0),customer);
	}
	
}
