/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package mission.laba1.ENUM;

/**
 *
 * @author aleksandra
 */
public enum Visibility {
    LOW,       
    MEDIUM,  
    HIGH,       
    ZERO,      
    UNKNOWN;
    
    public static Visibility fromString(String value) {
        if (value == null) return UNKNOWN;
        try {
            return Visibility.valueOf(value);
        } catch (IllegalArgumentException e) {
            return UNKNOWN;
        }
    }
}
