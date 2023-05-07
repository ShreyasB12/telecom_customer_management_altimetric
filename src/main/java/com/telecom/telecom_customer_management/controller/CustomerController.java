package com.telecom.telecom_customer_management.controller;

import com.telecom.telecom_customer_management.model.Customer;
import com.telecom.telecom_customer_management.model.RegisterCustomerRequest;
import com.telecom.telecom_customer_management.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class CustomerController {

    @Autowired
    CustomerService customerService;


    @RequestMapping(value="/customers/{customer_id}", method = RequestMethod.GET)
    public ResponseEntity<?> getCustomersProfileDetails(@RequestParam("customer_id") int customerId){
        Map<String, Object> response = new LinkedHashMap<>();
        Optional<Customer> optionalCustomer = customerService.getCustomerProfile(customerId);
        if(optionalCustomer.isPresent()){
            response.put("status", 1);
            response.put("data", optionalCustomer.get());
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        else{
            response.clear();
            response.put("status", 0);
            response.put("message", "Customer Profile Not Present");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value="/customers", method = RequestMethod.POST)
    public ResponseEntity<?> registerCustomerProfile(@RequestBody RegisterCustomerRequest registerCustomerRequest){
        Map<String, Object> response = new LinkedHashMap<>();
        customerService.registerCustomerProfile(registerCustomerRequest.getCustomer());
        response.put("status", 1);
        response.put("message", "Customer Profile Saved Successfully");
        return new ResponseEntity<>(response, HttpStatus.CREATED);

    }
}
