package com.telecom.telecom_customer_management.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.telecom.telecom_customer_management.dao.CustomerDao;
import com.telecom.telecom_customer_management.model.Customer;
import com.telecom.telecom_customer_management.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService {

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(CustomerService.class);

    @Autowired
    CustomerDao customerDao;

    @Autowired
    EmailService emailService;
    public void registerCustomerProfile(Customer customer){
        if(!validateCustomerPayload(customer)){
            log.error("Error while validating customer payload");
        }
        customerDao.registerCustomerProfile(customer);
        ObjectMapper mapper = new ObjectMapper();
        try {
            emailService.sendEmail(customer.getEmail(), "Customer registered profile successfully", mapper.writeValueAsString(customer));
        } catch (JsonProcessingException e) {
            log.error("Email is not sent successfully. Error in converting plan into string");
        }
    }

    public Optional<Customer> getCustomerProfile(int customerId){

        return customerDao.getCustomerProfile(customerId);
    }

    public boolean validateCustomerPayload(Customer customer){
        if(!CommonUtils.validateAadhar(customer.getAadhar())){
            log.error("Aadhar Number validation failed");
            return false;
        }
        if(!CommonUtils.validateMobile(customer.getMobNumber())){
            log.error("Mobile Number validation failed");
            return false;
        }
        return true;
    }
}
