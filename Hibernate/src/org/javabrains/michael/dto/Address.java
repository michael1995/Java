package org.javabrains.michael.dto;

import javax.persistence.Column;
import javax.persistence.Embeddable;




@Embeddable
public class Address {

	private String street;
	private String city;
	private String state;
	private String pinCode;
	
	
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	@Column (name="City_Name")
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getPinCode() {
		return pinCode;
	}
	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}
	
	
}
