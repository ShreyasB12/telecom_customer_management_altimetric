package com.telecom.telecom_customer_management.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.telecom.telecom_customer_management.dao.CustomerDao;
import com.telecom.telecom_customer_management.dao.CustomerPlanDao;
import com.telecom.telecom_customer_management.dao.PlanDao;
import com.telecom.telecom_customer_management.model.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class CustomerPlansService {

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(CustomerPlansService.class);

    @Autowired
    CustomerPlanDao customerPlanDao;

    @Autowired
    CustomerDao customerDao;

    @Autowired
    CustomerService customerService;

    @Autowired
    PlanDao planDao;

    @Autowired
    EmailService emailService;
    public void registerCustomerPlans(int planId, int customerId){

        CustomerPlan customerPlan = new CustomerPlan();
        Optional<Plan> currentPlan = planDao.getPlan(planId);
        if(currentPlan.isPresent()){
            Plan newPlan = Plan.builder()
                    .planCost(currentPlan.get().getPlanCost())
                    .planName(currentPlan.get().getPlanName())
                    .planId(currentPlan.get().getPlanId())
                    .planValidity(currentPlan.get().getPlanValidity()).build();
            customerPlan.setPlan(newPlan);
            Calendar cal = Calendar.getInstance();
            Date today = cal.getTime();
            customerPlan.setRenewalDate(today);
            cal.add(Calendar.DATE, newPlan.getPlanValidity().getValue());
            customerPlan.setExpiryDate(cal.getTime());
            customerPlanDao.registerCustomerPlan(customerPlan, customerId);
            Optional<Customer> customer = customerDao.getCustomerProfile(customerId);
            if(customer.isPresent()){
                ObjectMapper mapper = new ObjectMapper();
                try {
                    emailService.sendEmail(customer.get().getEmail(), "Customer registered plan successfully", mapper.writeValueAsString(newPlan));
                } catch (JsonProcessingException e) {
                    log.error("Email is not sent successfully. Error in converting plan into string");
                }
            }

        }
        else{
            log.error("Plan not supported");
        }

    }

    public Optional<CustomerCompleteDetails> getCustomerPlanDetails(int customerId) {
        Customer customer = customerDao.getCustomerProfile(customerId).get();
        CustomerPlan customerPlan = customerPlanDao.getCustomerPlanDetails(customerId);
        return Optional.ofNullable(CustomerCompleteDetails.builder().customer(customer).customerPlan(customerPlan).build());
    }

    public List<CustomerCompleteDetails> getAllCustomerPlanDetails(Sorting sort) {
        List<CustomerCompleteDetails> res = new ArrayList<>();
        Map<Integer, Customer> customers = customerDao.getAllCustomerProfile();
        Map<Integer, CustomerPlan> customerPlans = customerPlanDao.getAllCustomerPlans();
        customers.forEach((k, v) -> {
            CustomerCompleteDetails details = new CustomerCompleteDetails();
            details.setCustomer(v);
            if(customerPlans.containsKey(k)){
                details.setCustomerPlan(customerPlans.get(k));
            }
            res.add(details);
        });
        sortBasedOnCondition(res, sort);
        return res;
    }

    private void sortBasedOnCondition(List<CustomerCompleteDetails> res, Sorting sort) {
        Comparator<CustomerCompleteDetails> comparator = null;
        if(sort == null){
            comparator = Comparator.comparing(e -> e.getCustomer().getCustomerName());
            return;
        }
        String sortField = sort.getField();
        SortOrder sortOrder = sort.getSortOrder();

        if("renewalDate".equals(sortField)){
            if(sortOrder.equals(SortOrder.ASC)){
                comparator = Comparator.comparing(e -> e.getCustomerPlan().getRenewalDate());
            }
            else{
                comparator = Comparator.comparing(e -> e.getCustomerPlan().getRenewalDate(), Comparator.reverseOrder());
            }
        }
        else if("planName".equals(sortField)){
            if(sortOrder.equals(SortOrder.ASC)){
                comparator = Comparator.comparing(e -> e.getCustomerPlan().getPlan().getPlanName());
            }
            else{
                comparator = Comparator.comparing(e -> e.getCustomerPlan().getPlan().getPlanName(), Comparator.reverseOrder());
            }
        }
        else if("planValidity".equals(sortField)){
            if(sortOrder.equals(SortOrder.ASC)){
                comparator = Comparator.comparing(e -> e.getCustomerPlan().getPlan().getPlanValidity());
            }
            else{
                comparator = Comparator.comparing(e -> e.getCustomerPlan().getPlan().getPlanValidity(), Comparator.reverseOrder());
            }
        }
        else if("planCost".equals(sortField)){
            if(sortOrder.equals(SortOrder.ASC)){
                comparator = Comparator.comparing(e -> e.getCustomerPlan().getPlan().getPlanCost());
            }
            else{
                comparator = Comparator.comparing(e -> e.getCustomerPlan().getPlan().getPlanCost(), Comparator.reverseOrder());
            }
        }

        res.sort(comparator);
    }
}
