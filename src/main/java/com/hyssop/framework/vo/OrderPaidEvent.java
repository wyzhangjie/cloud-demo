package com.hyssop.framework.vo;

import java.io.Serializable;
import java.math.BigDecimal;

public class OrderPaidEvent implements Serializable {
    private String orderId;

    private BigDecimal paidMoney;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public BigDecimal getPaidMoney() {
        return paidMoney;
    }

    public void setPaidMoney(BigDecimal paidMoney) {
        this.paidMoney = paidMoney;
    }
}