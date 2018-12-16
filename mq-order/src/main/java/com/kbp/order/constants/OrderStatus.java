package com.kbp.order.constants;

/**
 * Created by kbp1234 on 2018/12/16.
 */
public enum OrderStatus {

    ORDER_CREATED("1"),
    ORDER_PAYED("2"),
    ORDER_FAIL("3");

    private String status;

    private OrderStatus(String status) {
        this.status = status;
    }

    public String getValue() {
        return status;
    }


}
