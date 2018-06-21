package com.intutx.ecommshopservice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.intutx.ecommshopservice.util.IdGenerator;

@Entity
@Table(name = "customer")
public class Customer {

	@Id
	@Column(name = "customer_id")
	private Long customerId;

	@Column(name = "login_id")
	private String loginId;

	@Column(name = "password")
	private String password;

	public Long getCustomerId() {
		return customerId;
	}

	public String getLoginId() {
		return loginId;
	}

	public String getPassword() {
		return password;
	}

	public Customer(Long customerId, String loginId, String password) {
		super();
		this.customerId = customerId;
		this.loginId = loginId;
		this.password = password;
	}

	public Customer(Customer inCust) {
		this.customerId = IdGenerator.getRandomId();
		this.loginId = inCust.getLoginId();
		this.password = inCust.getPassword();
	}

}