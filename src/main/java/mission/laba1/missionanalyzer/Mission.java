/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mission.laba1.missionanalyzer;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author aleksandra
 */
public class Mission {
    private String missionId;
    private String date;
    private String location;
    private String outcome;
    private int damageCost;
    
    private Curse curse; //ссылка на пустой объект
    private List<Sorcer> sorcerers;
    private List<Technique> techniques;
    
    
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
    
    public String getOutcome() { return outcome; }
    public void setOutcome(String outcome) { this.outcome = outcome; }
    
    public Integer getDamageCost() { return damageCost; }
    public void setDamageCost(int damageCost) { this.damageCost = damageCost; }
    
    public Curse getCurse() { return curse; }
    public void setCurse(Curse curse) { this.curse = curse; }
    
    public List<Sorcer> getSorcerers() { return sorcerers; }
    public void setSorcerers(List<Sorcer> sorcerers) { this.sorcerers = sorcerers; }
    
    public List<Technique> getTechniques() { return techniques; }
    public void setTechniques(List<Technique> techniques) { this.techniques = techniques; }
    
    
}
