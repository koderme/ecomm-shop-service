package com.intutx.ecommshopservice.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.intutx.ecommshopservice.model.Customer;
import com.intutx.ecommshopservice.model.CustomerProfile;
import com.intutx.ecommshopservice.service.CustomerService;

@Controller
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = RestUri.CUSTOMERS)
public class CustomerController {

	private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

	@Autowired
	private CustomerService customerService;
	

	// ---- helper methods
	private ResponseEntity<CustomerProfile> _toResponseEntity(CustomerProfile customer) {
		return customer != null ? new ResponseEntity<CustomerProfile>(customer, HttpStatus.OK)
				: new ResponseEntity<CustomerProfile>(HttpStatus.NOT_FOUND);
	}

	private ResponseEntity<List<CustomerProfile>> _toResponseEntity(List<CustomerProfile> customerList) {
		System.out.println("list size : " + customerList.size());
		return !customerList.isEmpty() ? new ResponseEntity<List<CustomerProfile>>(customerList, HttpStatus.OK)
				: new ResponseEntity<List<CustomerProfile>>(HttpStatus.NOT_FOUND);
	}

	// ---- register Customer
	@RequestMapping(path = RestUri.REGISTER, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody ResponseEntity<Boolean> register(@RequestBody Customer custToBeSaved) {

		logger.info("register customer {}", custToBeSaved);

		if (custToBeSaved == null)
			return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);

		boolean result = customerService.register(custToBeSaved);
		logger.info("result {}", result);
		return result ? new ResponseEntity<>(result, HttpStatus.OK)
				: new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
	}

	// ---- login
	@RequestMapping(path = RestUri.LOGIN, 
			method = RequestMethod.POST, 
			produces = MediaType.APPLICATION_JSON_UTF8_VALUE, 
			consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody ResponseEntity<Boolean> login(@RequestBody Customer cust) {

		logger.info("login request {}", cust);

		boolean result = customerService.login(cust);
		logger.info("result {}", result);
		return result ? new ResponseEntity<>(result, HttpStatus.OK)
				: new ResponseEntity<>(false, HttpStatus.UNAUTHORIZED);
	}

	// ---- save CustomerProfile
	@RequestMapping(path = RestUri.SAVE, method = RequestMethod.POST, 
			produces = MediaType.APPLICATION_JSON_UTF8_VALUE, 
			consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody ResponseEntity<Boolean> save(@RequestBody CustomerProfile custToBeSaved) {

		logger.info("create request {}", custToBeSaved);
		
		if (custToBeSaved == null)
			return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);

		boolean result = customerService.save(custToBeSaved);
		logger.info("result {}", result);
		return result ? new ResponseEntity<>(result, HttpStatus.OK)
				: new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
	}


	// ---- search by id 
	@GetMapping(path = "/{id}")
	public @ResponseBody ResponseEntity<CustomerProfile> getCustomerProfile(@PathVariable("id") Long customerId) {
		logger.info("searching for id: " + customerId);
		if (customerId == null || customerId <= 0L)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

		CustomerProfile customer = customerService.findByCustomerId(customerId);
		return _toResponseEntity(customer);
	}

	// ---- search by firstname, lastname
	@GetMapping(path = RestUri.SEARCH)
	public ResponseEntity<List<CustomerProfile>> getCustomerProfile(
			@RequestParam(name = "firstName", defaultValue = "") String firstName,
			@RequestParam(name = "lastName", defaultValue = "") String lastName) {

		logger.info("searching by firstName={} or lastName={} ", firstName, lastName);

		// If loginId is specified, ignore others
		if (firstName != null && !firstName.isEmpty()) {
			List<CustomerProfile> customerList = customerService.findByFirstName(firstName);
			return _toResponseEntity(customerList);

		} else if (lastName != null && !lastName.isEmpty()) {
			List<CustomerProfile> customerList = customerService.findByLastName(lastName);
			return _toResponseEntity(customerList);
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	
	// ---- list all customers
	@GetMapping()
	public @ResponseBody Iterable<CustomerProfile> getAll() {
		return customerService.getAll();
	}


}
