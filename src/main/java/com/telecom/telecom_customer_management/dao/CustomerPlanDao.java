package com.telecom.telecom_customer_management.dao;

import com.telecom.telecom_customer_management.DBConfig.DummyDatabase;
import com.telecom.telecom_customer_management.model.Customer;
import com.telecom.telecom_customer_management.model.CustomerPlan;
import com.telecom.telecom_customer_management.model.Plan;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class CustomerPlanDao {

    DummyDatabase dbInstance;
    CustomerPlanDao(){
        dbInstance = DummyDatabase.getInstance();
    }
    public void registerCustomerPlan(CustomerPlan customerPlan, int customerId){
        Map<Integer, CustomerPlan> customerPlanTable = dbInstance.getCustomerPlansTable();
        customerPlanTable.put(customerId, customerPlan);
    }

    public  CustomerPlan getCustomerPlanDetails(int customerId){
        Map<Integer, CustomerPlan> customerPlanTable = dbInstance.getCustomerPlansTable();
        return customerPlanTable.get(customerId);
    }

    public Map<Integer, CustomerPlan> getAllCustomerPlans() {
        return  dbInstance.getCustomerPlansTable();
    }
}
