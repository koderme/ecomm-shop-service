package com.intutx.ecommshopservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.intutx.ecommshopservice.model.Customer;
import com.intutx.ecommshopservice.service.CustomerService;

@Controller
@RequestMapping(path = "/customer")
public class CustomerController {

	private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

	private ResponseEntity<Customer> toResponseEntity(Customer customer) {
		return customer != null ? new ResponseEntity<Customer>(customer, HttpStatus.OK)
				: new ResponseEntity<Customer>(HttpStatus.NOT_FOUND);
	}
	
	@Autowired
	private CustomerService service;

	@GetMapping(path = "/all")
	public @ResponseBody Iterable<Customer> getAll() {
		return service.getAllCustomers();
	}

	@GetMapping(path = "/id/{id}")
	public ResponseEntity<Customer> getCustomer(@PathVariable("id") Long customerId) {
		logger.info("searching for id: " + customerId);
		if (customerId == null || customerId <= 0L)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

		Customer customer = service.getCustomerByCustomerId(customerId);
		return toResponseEntity(customer);
	}

	@GetMapping(path = "/login/{login}")
	public ResponseEntity<Customer> getCustomer(@PathVariable("login") String loginId) {
		logger.info("searching for login {} ", loginId);
		
		if (loginId == null || loginId.isEmpty())
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		
		Customer customer = service.getCustomerByLoginId(loginId);
		return toResponseEntity(customer);
	}

	@RequestMapping(path = "/create", 
			method = RequestMethod.POST, 
			produces = MediaType.APPLICATION_JSON_UTF8_VALUE, 
			consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody ResponseEntity<Boolean> create(@RequestBody Customer custToBeSaved) {

		if (custToBeSaved == null)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		
		boolean result = service.saveCustomer(custToBeSaved);
		return result ? new ResponseEntity<>(result, HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
