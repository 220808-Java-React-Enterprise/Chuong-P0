package com.revature.urbooks.models;

public class Order {
    private String id;
    private double subTotal;
    private double tax;
    private double grandTotal;

    public Order(String id, double subTotal, double tax, double grandTotal) {
        this.id = id;
        this.subTotal = subTotal;
        this.tax = tax;
        this.grandTotal = grandTotal;
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
}
