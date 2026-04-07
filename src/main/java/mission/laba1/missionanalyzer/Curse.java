/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mission.laba1.missionanalyzer;

import mission.laba1.ENUM.ThreatLevel;

/**
 *
 * @author aleksandra
 */
public class Curse {
    private String name;
    private ThreatLevel threatLevel;
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public ThreatLevel getThreatLevel() { return threatLevel; }
    public void setThreatLevel(ThreatLevel threatLevel) { this.threatLevel = threatLevel; }
    public void setThreatLevel(String threatLevel) { this.threatLevel = ThreatLevel.fromString(threatLevel); }
}
