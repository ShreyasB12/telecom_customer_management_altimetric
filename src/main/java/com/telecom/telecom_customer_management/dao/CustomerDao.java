package com.telecom.telecom_customer_management.dao;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.telecom.telecom_customer_management.DBConfig.DummyDatabase;
import com.telecom.telecom_customer_management.model.Customer;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;

@Repository
public class CustomerDao {

    DummyDatabase dbInstance;
    CustomerDao(){
        dbInstance = DummyDatabase.getInstance();
    }
    public void registerCustomerProfile(Customer customer){
        Map<Integer, Customer> customerTable = dbInstance.getCustomerTable();
        customerTable.put(customer.getCustomerId(), customer);
    }

    public Optional<Customer> getCustomerProfile(int customerId) {
        Map<Integer, Customer> customerTable = dbInstance.getCustomerTable();
        return Optional.ofNullable(customerTable.getOrDefault(customerId, null));
    }

    public Map<Integer, Customer> getAllCustomerProfile() {
        return  dbInstance.getCustomerTable();
    }
}
