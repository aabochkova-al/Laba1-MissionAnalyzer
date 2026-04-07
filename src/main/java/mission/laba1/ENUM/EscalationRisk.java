/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package mission.laba1.ENUM;

/**
 *
 * @author aleksandra
 */
public enum EscalationRisk {
    LOW,
    MEDIUM,    
    HIGH,
    UNKNOWN;
    
    public static EscalationRisk fromString(String value) {
        if (value == null) return UNKNOWN;
        try {
            return EscalationRisk.valueOf(value);
        } catch (IllegalArgumentException e) {
            return UNKNOWN;
        }
    }
}
