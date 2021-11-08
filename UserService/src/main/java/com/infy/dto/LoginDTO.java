package com.infy.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class LoginDTO {

	@Email(message = "{user.emailid.invalid}")
	@NotNull(message = "{user.emailid.absent}")
	private String emailId;
	
    @NotNull(message = "{user.password.absent}")
    @Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[!@#$%^&*]).{7,20}$", message = "{user.password.invalid}")
	private String password;
    

	
	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}


	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "LoginDTO [emailId=" + emailId + ", password=" + password + "]";
	}
	
}