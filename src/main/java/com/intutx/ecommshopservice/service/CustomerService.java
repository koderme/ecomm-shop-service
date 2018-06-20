package com.intutx.ecommshopservice.service;

import java.util.List;

import com.intutx.ecommshopservice.model.Customer;

public interface CustomerService {


	public List<Customer> getAllCustomers();

	public Customer findByCustomerId(Long customerId);
	public Customer findByLoginId(String loginId);
	public List<Customer> findByFirstName(String firstName);
	public List<Customer> findByLastName(String lastName);
	
	public List<String> suggestLoginIds(String pattern);
	
	public boolean saveCustomer(Customer customer);
	public void updateCustomer(Customer customer);
	public void removeCustomer(String customerId);
	
	
}