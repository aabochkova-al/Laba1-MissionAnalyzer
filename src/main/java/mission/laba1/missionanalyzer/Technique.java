/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mission.laba1.missionanalyzer;

/**
 *
 * @author aleksandra
 */
public class Technique {
    private String name;
    private String type;
    private String owner;
    private int damage;
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    
    public String getOwner() { return owner; }
    public void setOwner(String owner) { this.owner = owner; }
    
    public Integer getDamage() { return damage; }
    public void setDamage(int damage) { this.damage = damage; }
}
