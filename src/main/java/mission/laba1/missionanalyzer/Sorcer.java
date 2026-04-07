/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mission.laba1.missionanalyzer;

import mission.laba1.ENUM.SorcererRank;

/**
 *
 * @author aleksandra
 */
public class Sorcer {
    private String name;
    private SorcererRank rank;
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public SorcererRank getRank() { return rank; }
    public void setRank(SorcererRank rank) { this.rank = rank; }
    public void setRank(String rank) { this.rank = SorcererRank.fromString(rank); }
}
