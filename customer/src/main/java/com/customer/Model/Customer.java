package com.customer.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.springframework.stereotype.Component;

@Component
@Entity
@Table
public class Customer {
    @Id
    private String customerId;
    private String customerName;
    private String mobileNumber;
    private String customerBillId;

    public Customer() {
    }

    public Customer(String customerId, String customerName, String mobileNumber, String customerBillId) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.mobileNumber = mobileNumber;
        this.customerBillId = customerBillId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getCustomerBillId() {
        return customerBillId;
    }

    public void setCustomerBillId(String customerBillId) {
        this.customerBillId = customerBillId;
    }
}
