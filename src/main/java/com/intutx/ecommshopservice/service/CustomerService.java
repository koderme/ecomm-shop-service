package com.intutx.ecommshopservice.service;

import java.util.List;

import com.intutx.ecommshopservice.model.Customer;

public interface CustomerService {


	public Customer getCustomerByCustomerId(Long customerId);
	public Customer getCustomerByLoginId(String loginId);
	
	public List<Customer> getAllCustomers();
	
	public boolean saveCustomer(Customer customer);
	public void updateCustomer(Customer customer);
	public void removeCustomer(String customerId);
	
	public List<String> suggestLogin(String pattern);
	
}