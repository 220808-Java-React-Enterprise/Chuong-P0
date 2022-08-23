package com.revature.urbooks.models;

import java.sql.Date;

public class Order {
    private String id;
    private double subTotal;
    private double tax;
    private double grandTotal;

    private boolean status;
    private User user;

    private String userId;

    private java.sql.Date sqlDate;

    public Order(String id, double subTotal, double tax, double grandTotal, User user) {
        this.id = id;
        this.subTotal = subTotal;
        this.tax = tax;
        this.grandTotal = grandTotal;
        this.user = user;
    }

    public Date getSqlDate() {
        return sqlDate;
    }

    public void setSqlDate(Date sqlDate) {
        this.sqlDate = sqlDate;
    }

    public Order(String id, double subTotal, double tax, double grandTotal, java.sql.Date sqlDate, boolean status, String usserId) {
        this.id = id;
        this.subTotal = subTotal;
        this.tax = tax;
        this.grandTotal = grandTotal;
        this.sqlDate = sqlDate;
        this.userId = usserId;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }

    public double getTax() {
        return tax;
    }

    public void setTax(double tax) {
        this.tax = tax;
    }

    public double getGrandTotal() {
        return grandTotal;
    }

    public void setGrandTotal(double grandTotal) {
        this.grandTotal = grandTotal;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
