/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mission.laba1.missionanalyzer;

/**
 *
 * @author aleksandra
 */
public class EconomicAssessment {
    private Integer totalDamageCost;
    private Integer infrastructureDamage;
    private Integer commercialDamage;
    private Integer transportDamage;
    private Integer recoveryEstimateDays;
    private Boolean insuranceCovered;
    
    public Integer getTotalDamageCost() { return totalDamageCost; }
    public void setTotalDamageCost(Integer totalDamageCost) { this.totalDamageCost = totalDamageCost; }
    public Integer getInfrastructureDamage() { return infrastructureDamage; }
    public void setInfrastructureDamage(Integer infrastructureDamage) { this.infrastructureDamage = infrastructureDamage; }
    public Integer getCommercialDamage() { return commercialDamage; }
    public void setCommercialDamage(Integer commercialDamage) { this.commercialDamage = commercialDamage; }
    public Integer getTransportDamage() { return transportDamage; }
    public void setTransportDamage(Integer transportDamage) { this.transportDamage = transportDamage; }
    public Integer getRecoveryEstimateDays() { return recoveryEstimateDays; }
    public void setRecoveryEstimateDays(Integer recoveryEstimateDays) { this.recoveryEstimateDays = recoveryEstimateDays; }
    public Boolean getInsuranceCovered() { return insuranceCovered; }
    public void setInsuranceCovered(Boolean insuranceCovered) { this.insuranceCovered = insuranceCovered; }
}
