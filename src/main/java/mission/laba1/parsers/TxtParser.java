/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mission.laba1.parsers;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import mission.laba1.missionanalyzer.Curse;
import mission.laba1.missionanalyzer.Mission;
import mission.laba1.missionanalyzer.MissionBuilder;
import mission.laba1.missionanalyzer.Sorcer;
import mission.laba1.missionanalyzer.Technique;

/**
 *
 * @author aleksandra
 */
public class TxtParser extends BasicParser{

    @Override
    public Mission parse(String filepath) throws IOException {
        Map<String,String> firstData = fileToMapEdit(filepath);
        
  Map<String, Object> data = new HashMap<>();
        for (Map.Entry<String, String> entry : firstData.entrySet()) {
            data.put(entry.getKey(), entry.getValue());
        }
        
        if (firstData.containsKey("sorcerer[0].name")) {
            List<Map<String, String>> sorcerersList = new ArrayList<>();
            int index = 0;
            while (firstData.containsKey("sorcerer[" + index + "].name")) {
                Map<String, String> sorcerData = new HashMap<>();
                sorcerData.put("name", firstData.get("sorcerer[" + index + "].name"));
                sorcerData.put("rank", firstData.get("sorcerer[" + index + "].rank"));
                sorcerersList.add(sorcerData);
                index++;
            }
            data.put("sorcerers", sorcerersList);
        }
        
      
        if (firstData.containsKey("technique[0].name")) {
            List<Map<String, Object>> techniquesList = new ArrayList<>();
            int index = 0;
            while (firstData.containsKey("technique[" + index + "].name")) {
                Map<String, Object> techData = new HashMap<>();
                techData.put("name", firstData.get("technique[" + index + "].name"));
                techData.put("type", firstData.get("technique[" + index + "].type"));
                techData.put("owner", firstData.get("technique[" + index + "].owner"));
                String dmgStr = firstData.get("technique[" + index + "].damage");
                if (dmgStr != null) {
                    techData.put("damage", Integer.parseInt(dmgStr));
                }
                techniquesList.add(techData);
                index++;
            }
            data.put("techniques", techniquesList);
        }
        
       
        if (firstData.containsKey("weather") || firstData.containsKey("timeOfDay") || 
            firstData.containsKey("visibility") || firstData.containsKey("cursedEnergyDensity")) {
            
            Map<String, String> envData = new HashMap<>();
            if (firstData.containsKey("weather")) envData.put("weather", firstData.get("weather"));
            if (firstData.containsKey("timeOfDay")) envData.put("timeOfDay", firstData.get("timeOfDay"));
            if (firstData.containsKey("visibility")) envData.put("visibility", firstData.get("visibility"));
            if (firstData.containsKey("cursedEnergyDensity")) {
                envData.put("cursedEnergyDensity", firstData.get("cursedEnergyDensity"));
            }
            data.put("environment", envData);
        }
        
        MissionBuilder builder = new MissionBuilder();
        fillPasrserFromBasic(builder, data);
        
        return builder.build();
    }
    
    private Map<String, String> fileToMapEdit(String filepath) throws IOException {
        Map<String, String> data = new HashMap<>();
        String currentSection = "";
        int sectionCounter = 0;
        String previousSection = "";
        
        try (Scanner scanner = new Scanner(new File(filepath))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (line.isEmpty()) continue;
                
                if (line.startsWith("[") && line.endsWith("]")) {
                    String newSection = line.substring(1, line.length() - 1).toLowerCase();
                    
                    if (newSection.equals(previousSection)) {
                        sectionCounter++;
                    } else {
                        sectionCounter = 0;
                    }
                    previousSection = newSection;
                    currentSection = newSection;
                    continue;
                }
                
                int eqIndex = line.indexOf('=');
                if (eqIndex > 0) {
                    String key = line.substring(0, eqIndex).trim();
                    String value = line.substring(eqIndex + 1).trim();
                    
                    if (!currentSection.isEmpty() && !currentSection.equals("mission")) {
                        if (sectionCounter > 0) {
                            key = currentSection + "[" + sectionCounter + "]." + key;
                        } else {
                            key = currentSection + "." + key;
                        }
                    }
                    data.put(key, value);
                }
            }
        }
        return data;
    }

    @Override
    public String getExtension() {
      return "txt";  
    }
    
}
