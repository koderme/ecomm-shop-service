package com.intutx.ecommshopservice.controller;

import java.util.ArrayList;
import java.util.List;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.intutx.ecommshopservice.model.Customer;
import com.intutx.ecommshopservice.service.CustomerService;

@Controller
@RequestMapping(path = RestUri.CUSTOMER)
public class CustomerController {

	private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

	private ResponseEntity<Customer> toResponseEntity(Customer customer) {
		return customer != null ? new ResponseEntity<Customer>(customer, HttpStatus.OK)
				: new ResponseEntity<Customer>(HttpStatus.NOT_FOUND);
	}

	private ResponseEntity<List<Customer>> toResponseEntity(List<Customer> customerList) {
		System.out.println("list size : " + customerList.size());
		return !customerList.isEmpty() ? new ResponseEntity<List<Customer>>(customerList, HttpStatus.OK)
				: new ResponseEntity<List<Customer>>(HttpStatus.NOT_FOUND);
	}

	@Autowired
	private CustomerService service;

	@GetMapping()
	public @ResponseBody Iterable<Customer> getAll() {
		return service.getAllCustomers();
	}

	@GetMapping(path = "/{id}")
	public @ResponseBody ResponseEntity<Customer> getCustomer(@PathVariable("id") Long customerId) {
		logger.info("searching for id: " + customerId);
		if (customerId == null || customerId <= 0L)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

		Customer customer = service.findByCustomerId(customerId);
		return toResponseEntity(customer);
	}

	@GetMapping(path = RestUri.SEARCH)
	public ResponseEntity<List<Customer>> getCustomer(@RequestParam(name = "loginId", defaultValue = "") String loginId,
			@RequestParam(name = "firstName", defaultValue = "") String firstName,
			@RequestParam(name = "lastName", defaultValue = "") String lastName) {

		logger.info("searching by loginId={}, firstName={}, lastName={} ", loginId, firstName, lastName);

		// If loginId is specified, ignore others
		if (loginId != null && !loginId.isEmpty()) {
			Customer customer = service.findByLoginId(loginId);

			List<Customer> tempList = new ArrayList();
			tempList.add(customer);
			return toResponseEntity(tempList);
		} else if (firstName != null && !firstName.isEmpty()) {
			List<Customer> customerList = service.findByFirstName(firstName);
			return toResponseEntity(customerList);

		} else if (lastName != null && !lastName.isEmpty()) {
			List<Customer> customerList = service.findByLastName(lastName);
			return toResponseEntity(customerList);
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(path = "/create", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody ResponseEntity<Boolean> create(@RequestBody Customer custToBeSaved) {

		if (custToBeSaved == null)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

		boolean result = service.saveCustomer(custToBeSaved);
		return result ? new ResponseEntity<>(result, HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
