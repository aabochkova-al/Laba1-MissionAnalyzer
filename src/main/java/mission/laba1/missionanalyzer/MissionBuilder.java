/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mission.laba1.missionanalyzer;

import mission.laba1.ENUM.Outcome;

/**
 *
 * @author aleksandra
 */
public class MissionBuilder {
    private Mission mission;
    
    public MissionBuilder(){
        this.mission = new Mission();
    }
    
    public MissionBuilder setMissionId(String id){
        mission.setMissionId(id);
        return this;
    }
    
    public MissionBuilder setDate(String date) {
        mission.setDate(date);
        return this;
    }
    
    public MissionBuilder setLocation(String location) {
        mission.setLocation(location);
        return this;
    }
    
    public MissionBuilder setOutcome(Outcome outcome) {
        mission.setOutcome(outcome);
        return this;
    }
    
    public MissionBuilder setOutcome(String outcome) {
        mission.setOutcome(Outcome.fromString(outcome));
        return this;
    }
    
    public MissionBuilder setCurse(Curse curse) {
        mission.setCurse(curse);
        return this;
    }
    
    public MissionBuilder setDamageCost(Integer cost) {
        mission.setDamageCost(cost);
        return this;
    }
    
    public MissionBuilder setNotes(String notes) {
        mission.setNotes(notes);
        return this;
    }
    
    public MissionBuilder addSorcerer(Sorcer sorcer) {
        mission.getSorcerers().add(sorcer);
        return this;
    }
    
    public MissionBuilder addTechnique(Technique technique) {
        mission.getTechniques().add(technique);
        return this;
    }
    
    public MissionBuilder setEconomicAssessment(EconomicAssessment assessment) {
        mission.setEconomicAssessment(assessment);
        return this;
    }
    
    public MissionBuilder setEnvironment(EnvironmentConditions environment) {
        mission.setEnvironment(environment);
        return this;
    }
    
    public MissionBuilder addAdds(String key, Object value) {
        mission.addAdds(key, value);
        return this;
    }
    
    public Mission build() {
        return mission;
    }
    
}
