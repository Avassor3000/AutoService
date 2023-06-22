package com.example.autoservice.model;

public enum OrderStatus {
    TAKE("take"),
    INPROCESS("in process"),
    DONE("done"),
    NOTDONE("not done"),
    PAID("paid");

    private String value;

    OrderStatus(String value) {
        this.value = value;
    }
}
