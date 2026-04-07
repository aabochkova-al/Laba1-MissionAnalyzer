/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package mission.laba1.ENUM;

/**
 *
 * @author aleksandra
 */
public enum SorcererRank {
    SEMI_GRADE_2,
    GRADE_2,
    SEMI_GRADE_1,
    GRADE_1,
    UNKNOWN;
    
    
    public static SorcererRank fromString(String value) {
        if (value == null) return UNKNOWN;
        try {
            return SorcererRank.valueOf(value);
        } catch (IllegalArgumentException e) {
            return UNKNOWN;
        }
    }
}
