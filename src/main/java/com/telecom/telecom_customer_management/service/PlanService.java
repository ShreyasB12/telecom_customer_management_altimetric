package com.telecom.telecom_customer_management.service;

import com.telecom.telecom_customer_management.dao.PlanDao;
import com.telecom.telecom_customer_management.model.Plan;
import com.telecom.telecom_customer_management.model.RegisterPlanRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlanService {

    @Autowired
    PlanDao planDao;
    public List<Plan> registerNewPlans(RegisterPlanRequest registerPlan){

        registerPlan.getPlans().forEach(plan -> planDao.savePlan(plan));
        return registerPlan.getPlans();
    }

    public Plan updatePlan(Plan updatedPlan, int planId){

        planDao.updatePlan(updatedPlan, planId);
        return updatedPlan;
    }
}
