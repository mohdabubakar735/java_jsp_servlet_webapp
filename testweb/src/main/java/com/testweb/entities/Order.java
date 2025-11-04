package com.testweb.entities;

import java.io.Serializable;
import java.sql.Timestamp;

public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer orderId;
    private Timestamp orderDate;
    private Double totalAmount;
    private String description;

    public Order() {}

    // getters & setters
    
    public Integer getOrderId() {
        return orderId;
    }
    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }
    public Timestamp getOrderDate() {
        return orderDate;
    }
    public void setOrderDate(Timestamp orderDate) {
        this.orderDate = orderDate;
    }
    public Double getTotalAmount() {
        return totalAmount;
    }
    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Order [orderId=" + orderId + ", orderDate=" + orderDate + ", totalAmount=" + totalAmount
                + ", description=" + description + "]";
    }
}
