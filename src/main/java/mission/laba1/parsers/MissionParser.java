/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package mission.laba1.parsers;

import java.io.IOException;
import mission.laba1.missionanalyzer.Mission;
import mission.laba1.missionanalyzer.MissionBuilder;

/**
 *
 * @author aleksandra
 */
public interface MissionParser {
    Mission parse(MissionBuilder builder, String filepath) throws IOException;
    String getExtension();
}
