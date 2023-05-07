package com.telecom.telecom_customer_management.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class RegisterPlanRequest {
    List<Plan> plans = new ArrayList<>();
}
