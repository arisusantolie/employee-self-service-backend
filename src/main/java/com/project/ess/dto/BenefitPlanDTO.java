package com.project.ess.dto;

public class BenefitPlanDTO {
    private String name;
    private String description;
    private String eligibleType;
    private String claimMethod;

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
