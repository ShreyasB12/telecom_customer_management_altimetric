package com.telecom.telecom_customer_management.model;

public enum PlanValidity {
    DAY_365(365),
    DAY_180(180),
    DAY_90(90);

    private final int value;

    PlanValidity(final int newValue) {
        value = newValue;
    }

    public int getValue() { return value; }
}
