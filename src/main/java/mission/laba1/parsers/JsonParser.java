/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mission.laba1.parsers;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import mission.laba1.missionanalyzer.Mission;

/**
 *
 * @author aleksandra
 */
public class JsonParser implements MissionParser {
    private ObjectMapper mapper = new ObjectMapper();
    
    @Override
    public Mission parse(String filepath) throws IOException{
        File file = new File(filepath);
        return mapper.readValue(file, Mission.class);
    }
    
}
