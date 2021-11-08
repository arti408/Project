package com.infy.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class BuyerDTO {

	private Integer buyerId;

	@Email(message = "{user.emailid.invalid}")
	@NotNull(message = "{user.emailid.absent}")
	private String emailId;

	@NotNull(message = "{user.name.absent}")
	@Pattern(regexp = "[A-Za-z]+( [A-Za-z]+)*", message = "{user.name.invalid}")
	private String name;

	@NotNull(message = "{user.phoneno.absent}")
	@Size(min=10,max=10, message="{user.phoneno.invalid}")
	@Pattern(regexp="^[0-9]*$", message="{user.phoneno.invalid}")
	private String phoneNo;
	
    @NotNull(message = "{user.password.absent}")
    @Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[!@#$%^&*]).{7,20}$", message = "{user.password.invalid}")
	private String password;
    
	private String isPrivileged;
   
	private Double rewardPoints;
	
	private String isActive;
	
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

	
	public Integer getBuyerId() {
		return buyerId;
	}

	public void setBuyerId(Integer buyerId) {
		this.buyerId = buyerId;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getIsPrivileged() {
		return isPrivileged;
	}

	public void setIsPrivileged(String isPrivileged) {
		this.isPrivileged = isPrivileged;
	}

	public Double getRewardPoints() {
		return rewardPoints;
	}

	public void setRewardPoints(Double rewardPoints) {
		this.rewardPoints = rewardPoints;
	}

	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	@Override
	public String toString() {
		return "BuyerDTO [buyerId=" + buyerId + ", emailId=" + emailId + ", name=" + name + ", phoneNo=" + phoneNo
				+ ", password=" + password + ", isPrivileged=" + isPrivileged + ", rewardPoints=" + rewardPoints
				+ ", isActive=" + isActive + "]";
	}

	
}