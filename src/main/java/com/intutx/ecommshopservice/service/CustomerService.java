package com.intutx.ecommshopservice.service;

import java.util.List;

import com.intutx.ecommshopservice.model.Customer;
import com.intutx.ecommshopservice.model.CustomerProfile;

public interface CustomerService {

	// Customer
	public boolean login(Customer customer);
	public boolean isPresent(String loginId);
	public boolean register(Customer customer);

	// CustomerProfile
	public List<CustomerProfile> getAll();

	public CustomerProfile findByCustomerId(Long customerId);
	public List<CustomerProfile> findByFirstName(String firstName);
	public List<CustomerProfile> findByLastName(String lastName);
	
	public List<String> suggestLoginIds(String pattern);
	
	public boolean save(CustomerProfile customer);
	public void update(CustomerProfile customer);
	public void disable(String customerId);
	
	
}