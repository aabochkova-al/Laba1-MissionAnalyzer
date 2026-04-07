/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package mission.laba1.ENUM;

/**
 *
 * @author aleksandra
 */
public enum TechniqueType {
    INNATE,      
    WEAPON,      
    SHIKIGAMI,   
    BODY,     
    UNKNOWN;
    
    public static TechniqueType fromString(String value) {
        if (value == null) return UNKNOWN;
        try {
            return TechniqueType.valueOf(value);
        } catch (IllegalArgumentException e) {
            return UNKNOWN;
        }
    }
}
