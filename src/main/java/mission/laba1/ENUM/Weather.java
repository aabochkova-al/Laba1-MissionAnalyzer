/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package mission.laba1.ENUM;

/**
 *
 * @author aleksandra
 */
public enum Weather {
    CLEAR,         
    CLOUDY,        
    RAIN,          
    HEAVY_RAIN,     
    STORM,         
    FOG,           
    SNOW,          
    UNKNOWN;
    
    public static Weather fromString(String value) {
        if (value == null) return UNKNOWN;
        try {
            return Weather.valueOf(value);
        } catch (IllegalArgumentException e) {
            return UNKNOWN;
        }
    }
}
