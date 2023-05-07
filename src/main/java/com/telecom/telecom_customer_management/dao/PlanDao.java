package com.telecom.telecom_customer_management.dao;

import com.telecom.telecom_customer_management.DBConfig.DummyDatabase;
import com.telecom.telecom_customer_management.model.Plan;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class PlanDao {
    DummyDatabase dbInstance;
    PlanDao(){
        dbInstance = DummyDatabase.getInstance();
    }
    public void savePlan(Plan newPlan){
        Map<Integer, Plan> planTable = dbInstance.getPlanTable();
        planTable.put(newPlan.getPlanId(), newPlan);
        System.out.println(planTable);
    }

    public void updatePlan(Plan updatedPlan, int planId){
        Map<Integer, Plan> planTable = dbInstance.getPlanTable();
        planTable.put(planId, updatedPlan);
    }

    public Optional<Plan> getPlan(int planId){
        Map<Integer, Plan> planTable = dbInstance.getPlanTable();
        Optional<Plan> optionalPlan = Optional.ofNullable(planTable.getOrDefault(planId, null));
        return optionalPlan;
    }
}
