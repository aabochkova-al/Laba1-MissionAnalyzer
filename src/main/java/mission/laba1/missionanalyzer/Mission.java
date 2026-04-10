/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mission.laba1.missionanalyzer;

import com.fasterxml.jackson.annotation.JsonSetter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import mission.laba1.ENUM.Outcome;

/**
 *
 * @author aleksandra
 */
public class Mission {
    private String missionId;
    private String date;
    private String location;
    private Outcome outcome;
    private Integer damageCost;
    private String notes;
    
    private Curse curse; //ссылка на пустой объект
    private List<Sorcer> sorcerers;
    private List<Technique> techniques;
    private EconomicAssessment economicAssessment;
    private EnvironmentConditions environment;
    
    private Map<String, Object> adds = new HashMap<>();
    
    public Mission(){
        this.sorcerers = new ArrayList<>();
        this.techniques = new ArrayList<>();
    }
    
    public String getMissionId() { return missionId; }
    public void setMissionId(String missionId) { this.missionId = missionId; }
    
    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }
    
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
    
    public Outcome getOutcome() { return outcome; }
    public void setOutcome(Outcome outcome) { this.outcome = outcome; }
    public void setOutcome(String outcome) { this.outcome = Outcome.fromString(outcome); }
    
    public Integer getDamageCost() { return damageCost; }
    public void setDamageCost(Integer damageCost) { this.damageCost = damageCost; }
    
    public Curse getCurse() { return curse; }
    public void setCurse(Curse curse) { this.curse = curse; }
    
    public List<Sorcer> getSorcerers() { return sorcerers; }
    public void setSorcerers(List<Sorcer> sorcerers) { this.sorcerers = sorcerers; }
    
    public List<Technique> getTechniques() { return techniques; }
    public void setTechniques(List<Technique> techniques) { this.techniques = techniques; }
    
    public EconomicAssessment getEconomicAssessment() { return economicAssessment; }
    public void setEconomicAssessment(EconomicAssessment economicAssessment) { this.economicAssessment = economicAssessment; }
    
    public EnvironmentConditions getEnvironment() { return environment; }
    public void setEnvironment(EnvironmentConditions environment) { this.environment = environment; }
    
    public Map<String, Object> getExtensions() { return adds; }
    public void setExtensions(Map<String, Object> extensions) {
        this.adds = extensions;
    }
    public void addAdds(String key, Object value) {
        adds.put(key, value);
    }
    
    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }
    
    @JsonSetter("comment")
    public void setComment(String comment){
        this.notes=comment;
    }
}
