package com.telecom.telecom_customer_management.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Sorting {
    String field;
    SortOrder sortOrder;
}
