package com.TapFoods.model;

public class user {
	private int userId;
	private String username;
	private String email;
	private String phoneNumber;
	private String password;
	private String address;
	
	public user() {
		super();
	}

	public user(int userId, String username, String email, String phoneNumber, String password, String address) {
		super();
		this.userId = userId;
		this.username = username;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.password = password;
		this.address = address;
	}

	public user(String username, String email, String phoneNumber, String password, String address) {
		super();
		this.username = username;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.password = password;
		this.address = address;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "user [userId=" + userId + ", username=" + username + ", email=" + email + ", phoneNumber=" + phoneNumber
				+ ", password=" + password + ", address=" + address + "]";
	}
	
	
	

}
