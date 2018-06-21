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

	Customer cust1;
	List<Customer> custList1;
	
	Customer cust2;
	List<Customer> custList2;
	ObjectMapper pojo2Json;

	{
		this.cust1 = new Customer(111L, "aby", "Mr", "Aby", "M", "White", "N");
		this.custList1 = new ArrayList<>();
		this.custList1.add(cust1);
		
		this.cust2 = new Customer(222L, "boris", "Mr", "Boris", "M", "Becker", "N");
		this.custList2 = new ArrayList<>();
		this.custList2.add(cust2);
		this.pojo2Json = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
	
	}
	
	
	@Test
	public void customer_invalid_request_test() throws Exception {

		// Wire the mock...to replace actual service
		Mockito.when(service.findByCustomerId(0L)).thenReturn(null);

		// Create request --- null or 0 is considered bad request
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(RestUri.CUSTOMERS + "/0").accept(MediaType.APPLICATION_JSON);

		// Call rest service
		MvcResult response = mockMvc.perform(requestBuilder).andReturn();

		// Verify status code
		assertEquals("bad request test", HttpStatus.BAD_REQUEST.value(), response.getResponse().getStatus());
	}

	@Test
	public void customer_valid_request_test() throws Exception {

		// Wire the mock...to replace actual service
		Mockito.when(service.findByCustomerId(cust1.getCustomerId())).thenReturn(cust1);

		// Create request
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(RestUri.CUSTOMERS + "/" + cust1.getCustomerId()).accept(MediaType.APPLICATION_JSON);

		// Call rest service
		MvcResult response = mockMvc.perform(requestBuilder).andReturn();

		// The expected
		String expected = pojo2Json.writeValueAsString(cust1);
		// expected
		// ="{customerId:123,loginId:dummy,prefix:Mr,firstName:First,middleName:M,lastName:Last,phoneVerified:N}";

		// Verify status code
		assertEquals("compare status code", HttpStatus.OK.value(), response.getResponse().getStatus());

		// Verify response
		JSONAssert.assertEquals(expected, response.getResponse().getContentAsString(), false);
	}
	
	@Test
	public void customer_search_invalid_request_test() throws Exception {

		// Wire the mock...to replace actual service
		Mockito.when(service.findByLoginId(cust1.getLoginId())).thenReturn(cust1);

		// Create request
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(RestUri.CUSTOMERS_SEARCH).accept(MediaType.APPLICATION_JSON);

		// Call rest service
		MvcResult response = mockMvc.perform(requestBuilder).andReturn();

		// Verify status code
		assertEquals("compare status code", HttpStatus.BAD_REQUEST.value(), response.getResponse().getStatus());

	}
	
	@Test
	public void customer_search_loginId_specified_test() throws Exception {

		// Wire the mock...to replace actual service
		Mockito.when(service.findByLoginId(cust1.getLoginId())).thenReturn(cust1);

		// Create request
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(RestUri.CUSTOMERS_SEARCH + "?loginId=" + cust1.getLoginId()).accept(MediaType.APPLICATION_JSON);

		// Call rest service
		MvcResult response = mockMvc.perform(requestBuilder).andReturn();

		// The expected
		String expected = pojo2Json.writeValueAsString(custList1);
		
		// Verify status code
		assertEquals("compare status code", HttpStatus.OK.value(), response.getResponse().getStatus());

		// Verify response
		JSONAssert.assertEquals(expected, response.getResponse().getContentAsString(), false);
	}
	
	
	@Test
	public void customer_search_firstname_specified_test() throws Exception {

		// Wire the mock...to replace actual service
		Mockito.when(service.findByFirstName(cust1.getFirstName())).thenReturn(custList1);

		// Create request
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(RestUri.CUSTOMERS_SEARCH + "?firstName=" + cust1.getFirstName()).accept(MediaType.APPLICATION_JSON);

		// Call rest service
		MvcResult response = mockMvc.perform(requestBuilder).andReturn();

		// The expected
		String expected = pojo2Json.writeValueAsString(custList1);
		
		// Verify status code
		assertEquals("compare status code", HttpStatus.OK.value(), response.getResponse().getStatus());

		// Verify response
		JSONAssert.assertEquals(expected, response.getResponse().getContentAsString(), false);
	}
	
	
	@Test
	public void customer_search_lastname_specified_test() throws Exception {

		// Wire the mock...to replace actual service
		Mockito.when(service.findByLastName(cust1.getLastName())).thenReturn(custList1);

		// Create request
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(RestUri.CUSTOMERS_SEARCH + "?lastName=" + cust1.getLastName()).accept(MediaType.APPLICATION_JSON);

		// Call rest service
		MvcResult response = mockMvc.perform(requestBuilder).andReturn();

		// The expected
		String expected = pojo2Json.writeValueAsString(custList1);
		
		// Verify status code
		assertEquals("compare status code", HttpStatus.OK.value(), response.getResponse().getStatus());

		// Verify response
		JSONAssert.assertEquals(expected, response.getResponse().getContentAsString(), false);
	}
	
	
	@Test
	public void customer_save_invalid_request_test() throws Exception {

		// Wire the mock...to replace actual service
		Mockito.when(service.saveCustomer(cust1)).thenReturn(false);
		
		// Create request
		//RequestBuilder requestBuilder = MockMvcRequestBuilders.post(RestUri.CUSTOMERS_CREATE).accept(MediaType.APPLICATION_JSON_UTF8_VALUE);
		String requestBody = pojo2Json.writeValueAsString(cust1);
		RequestBuilder requestBuilder = MockMvcRequestBuilders
											.post(RestUri.CUSTOMERS_CREATE)
											.accept(MediaType.APPLICATION_JSON).content(requestBody)
											.contentType(MediaType.APPLICATION_JSON);

		// Call rest service
		MvcResult response = mockMvc.perform(requestBuilder).andReturn();

		// Verify status code
		assertEquals("compare status code", HttpStatus.BAD_REQUEST.value(), response.getResponse().getStatus());
		// Verify return
		String expected = pojo2Json.writeValueAsString(false);
		assertEquals(expected, response.getResponse().getContentAsString());

	}
	
	@Test
	public void customer_save_valid_request_test() throws Exception {

		// Wire the mock...to replace actual service
		Mockito.when(service.saveCustomer(cust1)).thenReturn(true);
		
		// Create request
		//RequestBuilder requestBuilder = MockMvcRequestBuilders.post(RestUri.CUSTOMERS_CREATE).accept(MediaType.APPLICATION_JSON_UTF8_VALUE);
		String requestBody = pojo2Json.writeValueAsString(cust1);
		RequestBuilder requestBuilder = MockMvcRequestBuilders
											.post(RestUri.CUSTOMERS_CREATE)
											.accept(MediaType.APPLICATION_JSON).content(requestBody)
											.contentType(MediaType.APPLICATION_JSON);

		// Call rest service
		MvcResult response = mockMvc.perform(requestBuilder).andReturn();

		// Verify status code
		assertEquals("compare status code", HttpStatus.OK.value(), response.getResponse().getStatus());
		// Verify return
		String expected = pojo2Json.writeValueAsString(true);
		assertEquals(expected, response.getResponse().getContentAsString());

	}
	
	
}