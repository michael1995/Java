package org.javabrains.michael.dto;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.Table;

@Entity
@Table (name = "UserTable")
public class UserDetails {

	
	private int UserID;	
	private String UserName;
	@Embedded
	private Address address;
	
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	//@ Column (name = "User ID")
	public int getUserID() {
		return UserID;
	}
	
	public void setUserID(int userID) {
		UserID = userID;
	}
	
	//@Column (name = "User Name")
	public String getUserName() {
		return UserName;
	}
	public void setUserName(String userName) {
		UserName = userName;
	}
	
	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
	
	
	
	
}
