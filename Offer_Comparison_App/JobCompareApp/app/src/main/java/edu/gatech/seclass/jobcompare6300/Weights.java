package edu.gatech.seclass.jobcompare6300;

public class Weights {
    private static Weights instance = null;

    private int commuteTimeWeight = 1;
    private int yearlySalaryWeight = 1;
    private int yearlyBonusWeight = 1;
    private int retirementBenefitsWeight = 1;
    private int leaveTimeWeight = 1;

    public void setCommuteTimeWeight(int commuteTimeWeight) {
        if (commuteTimeWeight >= 0 && commuteTimeWeight <= 10) {
            this.commuteTimeWeight = commuteTimeWeight;
        }
    }

    public void setYearlySalaryWeight(int yearlySalaryWeight) {
        if (yearlySalaryWeight >= 0 && yearlySalaryWeight <= 10) {
            this.yearlySalaryWeight = yearlySalaryWeight;
        }
    }

    public void setYearlyBonusWeight(int yearlyBonusWeight) {
        if (yearlyBonusWeight >= 0 && yearlyBonusWeight <= 10) {
            this.yearlyBonusWeight = yearlyBonusWeight;
        }

    }

    public void setRetirementBenefitsWeight(int retirementBenefitsWeight) {
        if (retirementBenefitsWeight >=0 && retirementBenefitsWeight <= 10) {
            this.retirementBenefitsWeight = retirementBenefitsWeight;
        }
    }

    public void setLeaveTimeWeight(int leaveTimeWeight) {
        if (leaveTimeWeight >= 0 && leaveTimeWeight <= 10) {
            this.leaveTimeWeight = leaveTimeWeight;
        }
    }

    public int getCommuteTimeWeight() {
        return commuteTimeWeight;
    }

    public int getYearlySalaryWeight() {
        return yearlySalaryWeight;
    }

    public int getYearlyBonusWeight() {
        return yearlyBonusWeight;
    }

    public int getRetirementBenefitsWeight() {
        return retirementBenefitsWeight;
    }

    public int getLeaveTimeWeight() {
        return leaveTimeWeight;
    }

    // get instance
    public static Weights getInstance(){
        if (instance ==null){
            instance = new Weights();
        }
        return instance;
    }
}
