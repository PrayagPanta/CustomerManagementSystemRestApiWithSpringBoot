package com.example.customerManagementSystem;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import com.example.customerManagementSystem.controllers.CustomerManagementController;
import com.example.customerManagementSystem.entities.Customer;
import com.example.customerManagementSystem.exceptions.CustomerNotFoundException;
import com.example.customerManagementSystem.services.CustomerService;

@WebMvcTest(CustomerManagementController.class)
public class CustomerManagementSystemVersion1ControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private CustomerService customerService;

	// private ObjectMapper mapper = new ObjectMapper();

	Customer customer = new Customer(1, "Prayag", "Panta", "prayagpanta@hotmail.com");
	Customer customer2 = new Customer(2, "User", "User", "user@gmail.com");

	// Testing get All Customers
	@Test
	public void getAllCustomers() throws Exception {
		List<Customer> customerList = new ArrayList<Customer>(List.of(customer, customer2));

		when(customerService.getAllCustomers()).thenReturn(customerList);

		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/getAllCustomers").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(jsonPath("$").isNotEmpty())
				.andExpect(jsonPath("$[0].firstName").value("Prayag"))
				.andExpect(jsonPath("$[1].emailId").value("user@gmail.com")).andDo(MockMvcResultHandlers.print());
		;
	}

	// Testing finding a customer by Id
	@Test
	public void findCustomerById_success() throws Exception {

		when(customerService.findCustomer(1)).thenReturn(customer);

		mockMvc.perform(
				MockMvcRequestBuilders.get("/api/v1/findCustomerById/1").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(jsonPath("$.firstName").value("Prayag"))
				.andDo(MockMvcResultHandlers.print());
	}

	// Testing finding a customer by Id which does not exist

	@Test
	public void findCustomerById_failure() throws Exception {
		when(customerService.findCustomer(2))
				.thenThrow(new CustomerNotFoundException("A customer with the provided Id does not exist"));

		mockMvc.perform(
				MockMvcRequestBuilders.get("/api/v1/findCustomerById/2").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound())
				.andExpect(jsonPath("$.Reason").value("A customer with the provided Id does not exist"))
				.andDo(MockMvcResultHandlers.print());
	}

	// Testing creating a customer
	@Test
	public void createCustomer_success() throws Exception {
		when(customerService.saveCustomer(Mockito.any(Customer.class))).thenReturn(customer);

		String exampleCustomerJson = "{\"firstName\":\"Prayag\",\"lastName\":\"Panta\",\"emailId\":\"prayagpanta@hotmail.com\"}";
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/api/v1/addCustomer")
				.accept(MediaType.APPLICATION_JSON).content(exampleCustomerJson)
				.contentType(MediaType.APPLICATION_JSON);

		mockMvc.perform(mockRequest).andExpect(status().isCreated()).andExpect(jsonPath("$").isNotEmpty())
				.andExpect(jsonPath("$.firstName").value("Prayag")).andDo(MockMvcResultHandlers.print());

	}
	
	//deleting a customer
	@Test
	public void deleteCustomer_success() throws Exception {
          when(customerService.deleteCustomer(Mockito.anyLong())).thenReturn((long) 1);
          
          mockMvc.perform(MockMvcRequestBuilders
                  .delete("/api/v1/deleteCustomerById/1")
                  .contentType(MediaType.APPLICATION_JSON))
                  .andExpect(status().isOk());
	}
	
    //failing to delete a customer
	@Test
	public void updateCustomer_success() throws Exception {
		//Use Injected bean for this method??
		Customer customer3 = new Customer(1, "Prayag", "Panta", "prayagpanta@hotmail.com");
		
		String exampleCustomerJson = "prayagpanta335@gmail.com";
		customer3.setEmailId("prayagpanta335@gmail.com");
		when(customerService.updateEmail(Mockito.anyLong(),Mockito.anyString())).thenReturn(customer3);
		
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.patch("/api/v1/updateEmailId/1")
				.accept(MediaType.APPLICATION_JSON).content(exampleCustomerJson)
				.contentType(MediaType.APPLICATION_JSON);
		mockMvc.perform(mockRequest).andExpect(status().isOk()).andExpect(jsonPath("$").isNotEmpty())
				.andExpect(jsonPath("$.emailId").value("prayagpanta335@gmail.com")).andDo(MockMvcResultHandlers.print());
	}
}
