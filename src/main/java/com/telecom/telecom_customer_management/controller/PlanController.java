package com.telecom.telecom_customer_management.controller;

import com.telecom.telecom_customer_management.model.Plan;
import com.telecom.telecom_customer_management.model.RegisterPlanRequest;
import com.telecom.telecom_customer_management.service.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@ResponseBody
public class PlanController {

    @Autowired
    PlanService planService;

    @RequestMapping(value="/plans", method = RequestMethod.POST)
    public List<Plan> registerNewPlans(@RequestBody RegisterPlanRequest registerPlanRequest){
        planService.registerNewPlans(registerPlanRequest);
        return registerPlanRequest.getPlans();
    }

    @RequestMapping(value="/plans/{plan_id}", method = RequestMethod.PUT)
    public Plan updateExistingPlan(@PathVariable("plan_id") int planId,
                                         @RequestBody Plan updatePlanRequest){
        planService.updatePlan(updatePlanRequest, planId);
        return updatePlanRequest;
    }
}
