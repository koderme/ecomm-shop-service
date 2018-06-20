package com.intutx.ecommshopservice.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.intutx.ecommshopservice.model.Customer;
import com.intutx.ecommshopservice.repo.CustomerRepository;
import com.intutx.ecommshopservice.util.IdGenerator;

@Component
public class CustomerServiceImpl implements CustomerService {
	private static final Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);

	@Autowired
	private CustomerRepository repo;

	@Override
	public List<Customer> getAllCustomers() {
		return (List<Customer>) repo.findAll();
	}

	@Override
	public Customer findByCustomerId(Long customerId) {
		Optional<Customer> obj = repo.findById(customerId);
		return obj.isPresent() ? obj.get() : null;
	}

	@Override
	public Customer findByLoginId(String loginId) {
		return repo.findByLoginId(loginId);
	}

	@Override
	public List<Customer> findByFirstName(String firstName) {
		return repo.findByFirstName(firstName);
	}

	@Override
	public List<Customer> findByLastName(String lastName) {
		return repo.findByLastName(lastName);
	}

	// --------------------------------------------------------------
	@Override
	public boolean saveCustomer(Customer inCust) {

		Customer customer = repo.findByLoginId(inCust.getLoginId());

		if (customer == null) {
			if (inCust.getCustomerId() != null) {
				logger.warn("Customer id {} contained in request will be overriden", inCust.getCustomerId());
			}
			inCust.setCustomerId(IdGenerator.getRandomId());
			logger.info("creating new customer {}", inCust);
			Customer newCustomer = repo.save(inCust);
			logger.info("created new customer {} ", newCustomer);
			return true;

		}

		logger.error("customer with login {} already exists", inCust.getLoginId());
		return false;
	}

	@Override
	public void updateCustomer(Customer customer) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeCustomer(String customerId) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<String> suggestLoginIds(String pattern) {
		// TODO Auto-generated method stub
		return null;
	}

}