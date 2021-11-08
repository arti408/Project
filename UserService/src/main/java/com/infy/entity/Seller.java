package com.infy.entity;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "seller")
public class Seller {

	@Id
	@Column(name = "sellerid", nullable = false)
	private Integer sellerId;
	private String name;
	@Column(name = "email", nullable = false)
	private String emailId;
	@Column(name = "phonenumber", nullable = false)
    private String phoneNo;
	private String password;
	@Column(name = "isactive", nullable = false)
	private String isActive;
	
	public Integer getSellerId() {
		return sellerId;
	}
	
	public void setSellerId(Integer sellerId) {
		this.sellerId = sellerId;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getEmailId() {
		return emailId;
	}
	
	public void setEmailId(String emailId) {
		this.emailId = emailId;
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
	
	public String getIsActive() {
		return isActive;
	}
	
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	@Override
	public int hashCode() {
		return Objects.hash(sellerId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Seller other = (Seller) obj;
		return Objects.equals(sellerId, other.sellerId);
	}

	@Override
	public String toString() {
		return "Seller [sellerId=" + sellerId + ", name=" + name + ", emailId=" + emailId + ", phoneNo=" + phoneNo
				+ ", password=" + password + ", isActive=" + isActive + "]";
	}
	
		
}