package com.laptrinhjavaweb.dto;


public class CustomerDTO extends BaseDTO {
	private static final long serialVersionUID = 2209884873340919536L;
	
	private String fullName;
	private String phone;
	private String email;
	
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
