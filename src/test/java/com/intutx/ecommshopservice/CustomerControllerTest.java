package com.intutx.ecommshopservice;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

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
import com.intutx.ecommshopservice.controller.RestUri;
import com.intutx.ecommshopservice.model.Customer;
import com.intutx.ecommshopservice.service.CustomerService;

@RunWith(SpringRunner.class)
@WebMvcTest(value = CustomerController.class, secure = false)
public class CustomerControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private CustomerService service;

	Customer mockCustomer;
	List<Customer> mockCustomerList;
	ObjectMapper pojo2Json;

	{
		this.mockCustomer = Customer.dummy;
		this.mockCustomerList = new ArrayList<>();
		this.mockCustomerList.add(mockCustomer);
		this.pojo2Json = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
	
	}
	
	
	@Test
	public void customer_invalid_request_Test() throws Exception {

		// Wire the mock...to replace actual service
		Mockito.when(service.findByCustomerId(0L)).thenReturn(null);

		// Create request --- null or 0 is considered bad request
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(RestUri.CUSTOMER + "/0").accept(MediaType.APPLICATION_JSON);

		// Call rest service
		MvcResult response = mockMvc.perform(requestBuilder).andReturn();

		// Verify status code
		assertEquals("bad request test", HttpStatus.BAD_REQUEST.value(), response.getResponse().getStatus());
	}

	@Test
	public void customer_valid_request_Test() throws Exception {

		// Wire the mock...to replace actual service
		Mockito.when(service.findByCustomerId(mockCustomer.getCustomerId())).thenReturn(mockCustomer);

		// Create request
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(RestUri.CUSTOMER + "/123").accept(MediaType.APPLICATION_JSON);

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
	
	@Test
	public void customer_search_invalid_request_Test() throws Exception {

		// Wire the mock...to replace actual service
		Mockito.when(service.findByLoginId(mockCustomer.getLoginId())).thenReturn(mockCustomer);

		// Create request
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(RestUri.CUSTOMER_SEARCH).accept(MediaType.APPLICATION_JSON);

		// Call rest service
		MvcResult response = mockMvc.perform(requestBuilder).andReturn();

		// Verify status code
		assertEquals("compare status code", HttpStatus.BAD_REQUEST.value(), response.getResponse().getStatus());

	}
	
	@Test
	public void customer_search_loginId_specified_Test() throws Exception {

		// Wire the mock...to replace actual service
		Mockito.when(service.findByLoginId(mockCustomer.getLoginId())).thenReturn(mockCustomer);

		// Create request
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(RestUri.CUSTOMER_SEARCH + "?loginId=dummy").accept(MediaType.APPLICATION_JSON);

		// Call rest service
		MvcResult response = mockMvc.perform(requestBuilder).andReturn();

		// The expected
		String expected = pojo2Json.writeValueAsString(mockCustomerList);
		
		// Verify status code
		assertEquals("compare status code", HttpStatus.OK.value(), response.getResponse().getStatus());

		// Verify response
		JSONAssert.assertEquals(expected, response.getResponse().getContentAsString(), false);
	}
	
	
	@Test
	public void customer_search_firstname_specified_Test() throws Exception {

		// Wire the mock...to replace actual service
		Mockito.when(service.findByFirstName(mockCustomer.getFirstName())).thenReturn(mockCustomerList);

		// Create request
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(RestUri.CUSTOMER_SEARCH + "?firstName=first").accept(MediaType.APPLICATION_JSON);

		// Call rest service
		MvcResult response = mockMvc.perform(requestBuilder).andReturn();

		// The expected
		String expected = pojo2Json.writeValueAsString(mockCustomerList);
		
		// Verify status code
		assertEquals("compare status code", HttpStatus.OK.value(), response.getResponse().getStatus());

		// Verify response
		JSONAssert.assertEquals(expected, response.getResponse().getContentAsString(), false);
	}
	
	
	@Test
	public void customer_search_lastname_specified_Test() throws Exception {

		// Wire the mock...to replace actual service
		Mockito.when(service.findByLastName(mockCustomer.getLastName())).thenReturn(mockCustomerList);

		// Create request
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(RestUri.CUSTOMER_SEARCH + "?lastName=last").accept(MediaType.APPLICATION_JSON);

		// Call rest service
		MvcResult response = mockMvc.perform(requestBuilder).andReturn();

		// The expected
		String expected = pojo2Json.writeValueAsString(mockCustomerList);
		
		// Verify status code
		assertEquals("compare status code", HttpStatus.OK.value(), response.getResponse().getStatus());

		// Verify response
		JSONAssert.assertEquals(expected, response.getResponse().getContentAsString(), false);
	}
}