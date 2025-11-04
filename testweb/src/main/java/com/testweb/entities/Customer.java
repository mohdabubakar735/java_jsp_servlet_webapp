package com.testweb.entities;

import java.io.Serializable;
import java.sql.Date;

public class Customer implements Serializable {
	/**
	 * 
	 */

    private static final long serialVersionUID = 1L;

    private Integer customerId;

    private String customerFirstName;
    
    private String customerLastName;

    private String customerAddress;

    private Integer customerAge;

    private String customerType;
    
    private String customerEmail;
    
    private String customerPhoneNumber;
    private Double custBalance;
    private String custSex;
    private String city;
    private String state;
    private String zipcode;
    private Date joinDate;

    public Customer() {
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getCustomerFirstName() {
        return customerFirstName;
    }

    public void setCustomerFirstName(String customerFirstName) {
        this.customerFirstName = customerFirstName;
    }
    public String getCustomerLastName() {
        return customerLastName;
    }

    public void setCustomerLastName(String customerLastName) {
        this.customerLastName = customerLastName;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }
    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getCustomerPhoneNumber() {
        return customerPhoneNumber;
    }

    public void setCustomerPhoneNumber(String customerPhoneNumber) {
        this.customerPhoneNumber = customerPhoneNumber;
    }
   
    public String getCustomerType() {
        return customerType;
    }
    

    public void setCustomerType(String customerType) {
        this.customerType = customerType;
    }


    public Integer getCustomerAge() {
        return customerAge;
    }

    public void setCustomerAge(Integer customerage) {
        this.customerAge = customerage;
    }

    public Double getCustBalance() {
        return custBalance;
    }

    public void setCustBalance(Double custBalance) {
        this.custBalance = custBalance;
    }

    public String getCustSex() {
        return custSex;
    }

    public void setCustSex(String custSex) {
        this.custSex = custSex;
    }

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

    public String getzipcode() {
        return zipcode;
    }

    public void setzipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public Date getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(Date joinDate) {
        this.joinDate = joinDate;
    }
    @Override
    public String toString() {
    	return "Customer [customerId=" + customerId
    	        + ", customerFirstName=" + customerFirstName
    	        + ", customerLastName=" + customerLastName
    	        + ", customerEmail=" + customerEmail
    	        + ", customerPhoneNumber=" + customerPhoneNumber
    	        + ", customerAddress=" + customerAddress
    	        + ", customerage=" + customerAge
    	        + ", city=" + city
    	        + ", state=" + state
    	        + ", zipCode=" + zipcode
    	        + "]";
                

           
    }
}
