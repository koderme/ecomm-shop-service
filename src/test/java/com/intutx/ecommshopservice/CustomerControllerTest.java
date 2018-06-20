package com.intutx.ecommshopservice;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.intutx.ecommshopservice.controller.CustomerController;
import com.intutx.ecommshopservice.model.Customer;
import com.intutx.ecommshopservice.service.CustomerService;

@RunWith(SpringRunner.class)
@WebMvcTest(value = CustomerController.class, secure = false)
public class CustomerControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private CustomerService service;

	Customer mockCustomer = Customer.dummy;
	ObjectMapper pojo2Json = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);

	
	
	@Test
	public void getCustomerByCustomerId_BadRequestTest() throws Exception {

		// Wire the mock...to replace actual service
		Mockito.when(service.getCustomerByCustomerId(mockCustomer.getCustomerId())).thenReturn(mockCustomer);

		// Create request --- null or 0 is considered bad request
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/customer/id/0").accept(MediaType.APPLICATION_JSON);

		// Call rest service
		MvcResult response = mockMvc.perform(requestBuilder).andReturn();

		// Verify status code
		assertEquals("bad request test", HttpStatus.BAD_REQUEST.value(), response.getResponse().getStatus());
	}

	@Test
	public void getCustomerByCustomerIdTest() throws Exception {

		// Wire the mock...to replace actual service
		Mockito.when(service.getCustomerByCustomerId(mockCustomer.getCustomerId())).thenReturn(mockCustomer);

		// Create request
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/customer/id/123").accept(MediaType.APPLICATION_JSON);

		// Call rest service
		MvcResult response = mockMvc.perform(requestBuilder).andReturn();

		// The expected
		String expected = pojo2Json.writeValueAsString(mockCustomer);
		// expected
		// ="{customerId:123,loginId:dummy,prefix:Mr,firstName:First,middleName:M,lastName:Last,phoneVerified:N}";

		// Verify status code
		assertEquals("compare status code", HttpStatus.OK.value(), response.getResponse().getStatus());

		// Verify response
		JSONAssert.assertEquals(expected, response.getResponse().getContentAsString(), false);
	}

	
}