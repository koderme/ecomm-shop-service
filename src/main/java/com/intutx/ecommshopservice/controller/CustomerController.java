package com.intutx.ecommshopservice.controller;


import java.util.Optional;

import javax.validation.Valid;

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
import com.intutx.ecommshopservice.repo.CustomerRepository;
import com.intutx.ecommshopservice.util.IdGenerator;

@Controller
@RequestMapping(path = "/customer")
public class CustomerController {
	

	private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);
	   

	@Autowired
	private CustomerRepository repo;

	@GetMapping(path = "/all")
	public @ResponseBody Iterable<Customer> getAll() {
		return repo.findAll();
	}

	@GetMapping(path = "/id/{id}")
	public ResponseEntity getCustomer(@PathVariable("id") Long id) {
		Optional<Customer> customer = repo.findById(id);
		logger.info("searching for id: " + id);
		return new ResponseEntity<Customer>(customer.get(), HttpStatus.FOUND);
	}

	@GetMapping(path = "/login/{login}")
	public ResponseEntity getCustomer(@PathVariable("login") String loginId) {

		Customer customer = repo.findByLoginId(loginId);
		logger.info("searching for login {} ", loginId);
		return new ResponseEntity<Customer>(customer, HttpStatus.FOUND);
	}

	@RequestMapping(path = "/create", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody ResponseEntity<?> create(@RequestBody @Valid Customer request) {

		Customer customer = repo.findByLoginId(request.getLoginId());
		if (customer == null) {
			if (request.getCustomerId() != null) {
				logger.warn("Customer id {} contained in request will be overriden", request.getCustomerId() );
			}
			request.setCustomerId(IdGenerator.getRandomId());
			logger.info("creating new customer {}", request);
			Customer newCustomer = repo.save(request);
			logger.info("created new customer {} ", newCustomer);
			return new ResponseEntity<Customer>(newCustomer, HttpStatus.CREATED);

		} else {
			logger.error("customer with login {} already exists", request.getLoginId());
			return new ResponseEntity<Customer>(customer, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

}
