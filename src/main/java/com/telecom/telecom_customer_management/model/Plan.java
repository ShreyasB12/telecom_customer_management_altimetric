package com.telecom.telecom_customer_management.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Plan {
    int planId;
    PlanName planName;
    PlanCost planCost;
    PlanValidity planValidity;
}
