package com.intutx.ecommshopservice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "customer")
public class Customer {

	@Id
	@Column(name = "customer_id")
	private Long customerId;

	@Column(name = "login_id")
	private String loginId;

	@Column(name = "prefix")
	private String prefix;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "middle_name")
	private String middleName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "phone_verified")
	private String phoneVerified;

	// Constructor
	public Customer() {
	}

	// Constructor
	public Customer(Long customerId, String loginId, String prefix, String firstName, String middleName,
			String lastName, String phoneVerified) {
		super();
		this.customerId = customerId;
		this.loginId = loginId;
		this.prefix = prefix;
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.phoneVerified = phoneVerified;
	}

	// Getter and Setter
	

		public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhoneVerified() {
		return phoneVerified;
	}

	public void setPhoneVerified(String phoneVerified) {
		this.phoneVerified = phoneVerified;
	}
	
	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", loginId=" + loginId + ", prefix=" + prefix + ", firstName="
				+ firstName + ", middleName=" + middleName + ", lastName=" + lastName + ", phoneVerified="
				+ phoneVerified + "]";
	}

}