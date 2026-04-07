/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package mission.laba1.ENUM;

/**
 *
 * @author aleksandra
 */
public enum TimeOfDay {
    MORNING,    
    AFTERNOON,  
    EVENING,    
    NIGHT,       
    UNKNOWN;
    
    public static TimeOfDay fromString(String value) {
        if (value == null) return UNKNOWN;
        try {
            return TimeOfDay.valueOf(value);
        } catch (IllegalArgumentException e) {
            return UNKNOWN;
        }
    }
}
