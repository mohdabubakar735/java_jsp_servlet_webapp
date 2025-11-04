package com.testweb.entities;

import java.sql.Timestamp;

public class Users{


	private String username;
	private String password;
	private Boolean isactive;
	private String email;
	private String role;
	private String createdby;
	private Timestamp createdat;
	
	public Users() {}
	
	private Integer userid;
	
	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getIsactive() {
		return isactive;
	}

	public void setIsactive(Boolean isactive) {
		this.isactive = isactive;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getCreatedby() {
		return createdby;
	}

	public void setCreatedby(String createdby) {
		this.createdby = createdby;
	}

	public Timestamp getCreatedat() {
		return createdat;
	}

	public void setCreatedat(Timestamp createdat) {
		this.createdat = createdat;
	}

	@Override
	public String toString() {
		return "users [username=" + username + ", password=" + password + ", isactive=" + isactive + ", email=" + email
				+ ", role=" + role + ", createdby=" + createdby + ", createdat=" + createdat + ", userid=" + userid + "]";
	}


}