package com.intutx.ecommshopservice.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "customer_profile")
public class CustomerProfile {

	@Id
	@Column(name = "customer_id")
	private Long customerId;

	@Column(name = "gender")
	private String gender;

	@Column(name = "prefix")
	private String prefix;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "middle_name")
	private String middleName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "dob")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-yy")
	private Date dob;

	@Column(name = "phone_number")
	private String phoneNumber;

	@Column(name = "phone_verified")
	private String phoneVerified;

	public CustomerProfile() {
		super();
	}
	public CustomerProfile(Long customerId, String gender, String prefix, String firstName, String middleName,
			String lastName, Date dob, String phoneNumber, String phoneVerified) {
		super();
		this.customerId = customerId;
		this.gender = gender;
		this.prefix = prefix;
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.dob = dob;
		this.phoneNumber = phoneNumber;
		this.phoneVerified = phoneVerified;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public String getGender() {
		return gender;
	}

	public String getPrefix() {
		return prefix;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public Date getDob() {
		return dob;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public String getPhoneVerified() {
		return phoneVerified;
	}

}