package com.infy.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class SellerDTO {

	private Integer sellerId;

	@Email(message = "{user.emailid.invalid}")
	@NotNull(message = "{user.emailid.absent}")
	private String emailId;

	@NotNull(message = "{user.name.absent}")
	@Pattern(regexp = "[A-Za-z]+( [A-Za-z]+)*", message = "{user.name.invalid}")
	private String name;

	@NotNull(message = "{user.phoneno.absent}")
	@Size(min=10,max=10, message="{user.phoneno.invalid}")
	@Pattern(regexp="^[0-9]*$", message="{user.phoneno.invalid}")
	private Integer phoneNo;
	
	@NotNull(message = "{user.password.absent}")
	@Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[!@#$%^&*]).{7,20}$", message = "{user.password.invalid}")
	private String password;

	private String isActive;
	
	public Integer getSellerId() {
		return sellerId;
	}
	
	public void setSellerId(Integer sellerId) {
		this.sellerId = sellerId;
	}
	
	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Integer getPhoneNo() {
		return phoneNo;
	}
	
	public void setPhoneNo(Integer phoneNo) {
		this.phoneNo = phoneNo;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getIsActive() {
		return isActive;
	}
	
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	@Override
	public String toString() {
		return "SellerDTO [sellerId=" + sellerId + ", emailId=" + emailId + ", name=" + name + ", phoneNo=" + phoneNo
				+ ", password=" + password + ", isActive=" + isActive + "]";
	}
	
}
