package com.telecom.telecom_customer_management.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Customer {
    int customerId;
    String customerName;
    String dateOfBirth;
    String email;
    String aadhar;
    Date registrationDate;
    String mobNumber;
}
