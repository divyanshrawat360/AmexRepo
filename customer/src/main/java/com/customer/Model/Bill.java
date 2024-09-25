package com.customer.Model;

public class Bill {
    private String billId;
    private int amount;
    private BillStatusEnum status;

    public Bill() {
    }

    public String getBillId() {
        return billId;
    }

    public void setBillId(String billId) {
        this.billId = billId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public BillStatusEnum getStatus() {
        return status;
    }

    public void setStatus(BillStatusEnum status) {
        this.status = status;
    }
}

