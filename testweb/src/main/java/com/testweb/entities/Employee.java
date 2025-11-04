package com.testweb.entities;

import java.io.Serializable;		
import java.sql.Timestamp;
	
public class Employee implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -707143426489538514L;

	private Integer empId;
	
	private String fullName;
	
	private Double balance;
	
	private String empType;
	
	private String empSex;
	
	private String city;
	
	private Timestamp joiningDt;
	
	public Employee() {
		// TODO Auto-generated constructor stub
	}

	public Integer getEmpId() {
		return empId;
	}

	public void setEmpId(Integer empId) {
		this.empId = empId;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public String getEmpType() {
		return empType;
	}

	public void setEmpType(String empType) {
		this.empType = empType;
	}

	public String getEmpSex() {
		return empSex;
	}

	public void setEmpSex(String empSex) {
		this.empSex = empSex;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Timestamp getJoiningDt() {
		return joiningDt;
	}

	public void setJoiningDt(Timestamp joiningDt) {
		this.joiningDt = joiningDt;
	}

	@Override
	public String toString() {
		return "Employee [empId=" + empId + ", fullName=" + fullName + ", balance=" + balance + ", empType=" + empType
				+ ", empSex=" + empSex + ", city=" + city + ", joiningDt=" + joiningDt + "]";
	}
	
	

}
