package com.telecom.telecom_customer_management.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class CustomerPlan {
    int customerId;
    Plan plan;
    Date renewalDate;
    Date expiryDate;
}
