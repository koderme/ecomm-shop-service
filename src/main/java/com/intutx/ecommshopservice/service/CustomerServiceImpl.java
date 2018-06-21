package com.intutx.ecommshopservice.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.intutx.ecommshopservice.model.Customer;
import com.intutx.ecommshopservice.model.CustomerProfile;
import com.intutx.ecommshopservice.repo.CustomerProfileRepository;
import com.intutx.ecommshopservice.repo.CustomerRepository;

@Component
public class CustomerServiceImpl implements CustomerService {
	private static final Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);

	@Autowired
	private CustomerRepository cRepo;

	@Autowired
	private CustomerProfileRepository cpRepo;

	// -----------------------------------
	// Methods for Customer
	// -----------------------------------
	@Override
	public boolean login(Customer customer) {
		Customer custInDb = cRepo.findByLoginId(customer.getLoginId());
		if (custInDb == null)
			return false;

		// TODO Decrypt the password
		return custInDb.getPassword().equals(customer.getPassword());
	}

	@Override
	public boolean isPresent(String loginId) {
		return cRepo.findByLoginId(loginId) != null;
	}

	@Override
	public boolean register(Customer inCust) {

		logger.info("saveCustomer");

		Customer custInDb = cRepo.findByLoginId(inCust.getLoginId());

		if (custInDb != null) {
			logger.error("Customer with loginId {} already exists", inCust.getLoginId());
			return false;
		}

		if (inCust.getCustomerId() != null) {
			logger.warn("Customer id {} contained in request will be overriden", inCust.getCustomerId());
		}

		// TODO encrypt the password before saving
		// TODO ensure password is strong
		Customer newCustomer = cRepo.save(new Customer(inCust));
		logger.info("created new customer {} ", newCustomer);
		return true;

	}

	// -----------------------------------
	// Methods for CustomerProfile
	// -----------------------------------

	@Override
	public List<CustomerProfile> getAll() {
		return (List<CustomerProfile>) cpRepo.findAll();
	}

	@Override
	public CustomerProfile findByCustomerId(Long customerId) {
		Optional<CustomerProfile> obj = cpRepo.findById(customerId);
		return obj.isPresent() ? obj.get() : null;
	}

	@Override
	public List<CustomerProfile> findByFirstName(String firstName) {
		return cpRepo.findByFirstName(firstName);
	}

	@Override
	public List<CustomerProfile> findByLastName(String lastName) {
		return cpRepo.findByLastName(lastName);
	}

	// --------------------------------------------------------------
	@Override
	public boolean save(CustomerProfile inCust) {

		CustomerProfile newCustomer = cpRepo.save(inCust);
		logger.info("saved customer {} ", newCustomer);
		return true;
	}

	@Override
	public List<String> suggestLoginIds(String pattern) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(CustomerProfile customer) {
		// TODO Auto-generated method stub

	}

	@Override
	public void disable(String customerId) {
		// TODO Auto-generated method stub

	}

}