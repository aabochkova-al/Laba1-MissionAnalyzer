/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mission.laba1.parsers;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import mission.laba1.missionanalyzer.Curse;
import mission.laba1.missionanalyzer.EconomicAssessment;
import mission.laba1.missionanalyzer.Mission;
import mission.laba1.missionanalyzer.MissionBuilder;
import mission.laba1.missionanalyzer.Sorcer;
import mission.laba1.missionanalyzer.Technique;

/**
 *
 * @author aleksandra
 */
public class JsonParser extends BasicParser {
    private ObjectMapper mapper = new ObjectMapper();
    
    @Override
    protected Map<String, Object> parseToMap(String filepath) throws IOException{
      return mapper.readValue(new File(filepath), Map.class);
    }

    @Override
    public String getExtension() {
        return "json";
    }
    
}
