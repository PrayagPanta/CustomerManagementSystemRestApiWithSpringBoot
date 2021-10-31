package com.example.customerManagementSystem;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import com.example.customerManagementSystem.controllers.CustomerManagementControllerVersion2;
import com.example.customerManagementSystem.entities.Customer;
import com.example.customerManagementSystem.services.CustomerService;

//does not work as intended
@WebMvcTest(CustomerManagementControllerVersion2.class)
public class CustomerSManagementSystemVersion2ControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private CustomerService customerService;

	Customer customer = new Customer(1, "Prayag", "Panta", "prayagpanta@hotmail.com");
	Customer customer2 = new Customer(2, "User", "User", "user@gmail.com");
	Pageable pageable = PageRequest.of(0,1);
	Page<Customer> customerPage = new PageImpl<>(List.of(customer,customer2),pageable,1);

	@Test
	public void getAllCustomers() throws Exception {

		when(customerService.getAllCustomers(Mockito.anyInt(),Mockito.anyInt())).thenReturn(customerPage);

		mockMvc.perform(
				MockMvcRequestBuilders.get("/api/v2/getAllCustomers/0/1").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[0].firstName").value("Prayag"))
				.andDo(MockMvcResultHandlers.print());

	}
}
