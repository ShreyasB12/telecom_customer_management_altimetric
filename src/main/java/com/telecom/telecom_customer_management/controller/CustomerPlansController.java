package com.telecom.telecom_customer_management.controller;

import com.telecom.telecom_customer_management.model.CustomerCompleteDetails;
import com.telecom.telecom_customer_management.model.CustomerPlanRequest;
import com.telecom.telecom_customer_management.service.CustomerPlansService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class CustomerPlansController {

    @Autowired
    CustomerPlansService customerPlansService;

    @GetMapping("/customer-plans")
    public ResponseEntity<?> getAllCustomerPlanDetails(@RequestBody CustomerPlanRequest customerPlanRequest){
        Map<String, Object> response = new LinkedHashMap<>();
        List<CustomerCompleteDetails>  customerCompleteDetails = customerPlansService.
                getAllCustomerPlanDetails(customerPlanRequest.getSort());
        if(!customerCompleteDetails.isEmpty()){
            response.put("status", 1);
            response.put("data", customerCompleteDetails);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        else{
            response.clear();
            response.put("status", 0);
            response.put("message", "Customer plan details not Found");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/customer-plans/{customer_id}")
    public ResponseEntity<?> getCustomerPlanDetails(@PathVariable("customer_id") int customerId){
        Map<String, Object> response = new LinkedHashMap<>();
        Optional<CustomerCompleteDetails> customerCompleteDetailsOptional = customerPlansService.
                getCustomerPlanDetails(customerId);
        if(customerCompleteDetailsOptional.isPresent()){
            response.put("status", 1);
            response.put("data", customerCompleteDetailsOptional.get());
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        else{
            response.clear();
            response.put("status", 0);
            response.put("message", "Customer Profile Not Present");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

    }


    @RequestMapping(value="/customer-plans/{customer_id}/{plan_id}", method = RequestMethod.PUT)
    public ResponseEntity<?> registerCustomerPlans(@PathVariable("customer_id") int customerId,
                                              @PathVariable("plan_id") int planId){
        customerPlansService.registerCustomerPlans(planId, customerId);
        Map<String, Object> response = new LinkedHashMap<>();
        customerPlansService.registerCustomerPlans(planId, customerId);
        response.put("status", 1);
        response.put("message", "Customer Plans registered Successfully");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
