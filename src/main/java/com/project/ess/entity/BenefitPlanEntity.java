package com.project.ess.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "benefitPlan")
public class BenefitPlanEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long benefitPlanId;

    private String name;
    private String description;
    private String eligibleType;
    private String claimMethod;

    public Long getBenefitPlanId() {
        return benefitPlanId;
    }

    public void setBenefitPlanId(Long benefitPlanId) {
        this.benefitPlanId = benefitPlanId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEligibleType() {
        return eligibleType;
    }

    public void setEligibleType(String eligibleType) {
        this.eligibleType = eligibleType;
    }

    public String getClaimMethod() {
        return claimMethod;
    }

    public void setClaimMethod(String claimMethod) {
        this.claimMethod = claimMethod;
    }
}
