package com.telecom.telecom_customer_management.DBConfig;

import com.telecom.telecom_customer_management.model.Customer;
import com.telecom.telecom_customer_management.model.CustomerPlan;
import com.telecom.telecom_customer_management.model.Plan;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;
import java.util.TreeMap;

@Getter
@Setter
public class DummyDatabase {

    private volatile static DummyDatabase planInstance;
    Map<Integer, Plan> planTable;
    Map<Integer, Customer> customerTable ;
    Map<Integer, CustomerPlan> customerPlansTable;
    DummyDatabase(){
        planTable =  new TreeMap<>();
        customerTable = new TreeMap<>();
        customerPlansTable = new TreeMap<>();
    }

    public static DummyDatabase getInstance(){
        if(planInstance == null){
            synchronized (DummyDatabase.class){
                if(planInstance == null){
                    planInstance = new DummyDatabase();
                }
            }
        }
        return planInstance;
    }
}
