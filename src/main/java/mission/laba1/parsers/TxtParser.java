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
    protected Map<String, Object> parseToMap(String filepath) throws IOException {
        Map<String, String> firstData = fileToMapEdit(filepath);
        return convertion(firstData);
    }

    private Map<String, Object> convertion(Map<String, String> firstData){
         Map<String, Object> result = new HashMap<>();
        
//        System.out.println("DEBUG: Распарсенные данные:");
//        for (Map.Entry<String, String> entry : firstData.entrySet()) {
//            System.out.println("  " + entry.getKey() + " = " + entry.getValue());
//        }
        
        if (firstData.containsKey("missionId")) result.put("missionId", firstData.get("missionId"));
        if (firstData.containsKey("date")) result.put("date", firstData.get("date"));
        if (firstData.containsKey("location")) result.put("location", firstData.get("location"));
        if (firstData.containsKey("outcome")) result.put("outcome", firstData.get("outcome"));
        if (firstData.containsKey("damageCost")) {
            try {
                result.put("damageCost", Integer.parseInt(firstData.get("damageCost")));
            } catch (NumberFormatException e) {}
        }
        
        if (firstData.containsKey("curse[0].name") || firstData.containsKey("curse[0].threatLevel")) {
            Map<String, String> curseData = new HashMap<>();
            if (firstData.containsKey("curse[0].name")) {
                curseData.put("name", firstData.get("curse[0].name"));
            }
            if (firstData.containsKey("curse[0].threatLevel")) {
                curseData.put("threatLevel", firstData.get("curse[0].threatLevel"));
            }
            result.put("curse", curseData);
        }
        
        List<Map<String, String>> sorcerersList = new ArrayList<>();
        int sorcererIndex = 0;
        while (firstData.containsKey("sorcerer[" + sorcererIndex + "].name")) {
            Map<String, String> sorcererData = new HashMap<>();
            sorcererData.put("name", firstData.get("sorcerer[" + sorcererIndex + "].name"));
            if (firstData.containsKey("sorcerer[" + sorcererIndex + "].rank")) {
                sorcererData.put("rank", firstData.get("sorcerer[" + sorcererIndex + "].rank"));
            }
            sorcerersList.add(sorcererData);
            sorcererIndex++;
        }
        if (!sorcerersList.isEmpty()) {
            result.put("sorcerers", sorcerersList);
        }
        
      
        List<Map<String, Object>> techniquesList = new ArrayList<>();
        int techniqueIndex = 0;
        while (firstData.containsKey("technique[" + techniqueIndex + "].name")) {
            Map<String, Object> techniqueData = new HashMap<>();
            techniqueData.put("name", firstData.get("technique[" + techniqueIndex + "].name"));
            if (firstData.containsKey("technique[" + techniqueIndex + "].type")) {
                techniqueData.put("type", firstData.get("technique[" + techniqueIndex + "].type"));
            }
            if (firstData.containsKey("technique[" + techniqueIndex + "].owner")) {
                techniqueData.put("owner", firstData.get("technique[" + techniqueIndex + "].owner"));
            }
            if (firstData.containsKey("technique[" + techniqueIndex + "].damage")) {
                String dmg = firstData.get("technique[" + techniqueIndex + "].damage");
                if (dmg != null && !dmg.isEmpty()) {
                    techniqueData.put("damage", Integer.parseInt(dmg));
                }
            }
            techniquesList.add(techniqueData);
            techniqueIndex++;
        }
        if (!techniquesList.isEmpty()) {
            result.put("techniques", techniquesList);
        }
        
       
        if (firstData.containsKey("environment[0].weather") || firstData.containsKey("environment[0].timeOfDay") || 
            firstData.containsKey("environment[0].visibility") || firstData.containsKey("environment[0].cursedEnergyDensity")) {
            
            Map<String, Object> envData = new HashMap<>();
            if (firstData.containsKey("environment[0].weather")) envData.put("weather", firstData.get("environment[0].weather"));
            if (firstData.containsKey("environment[0].timeOfDay")) envData.put("timeOfDay", firstData.get("environment[0].timeOfDay"));
            if (firstData.containsKey("environment[0].visibility")) envData.put("visibility", firstData.get("environment[0].visibility"));
            if (firstData.containsKey("environment[0].cursedEnergyDensity")) {
                envData.put("cursedEnergyDensity", firstData.get("environment[0].cursedEnergyDensity"));
            }
            result.put("environment", envData);
        }
        
        return result;
    }
    
    private Map<String, String> fileToMapEdit(String filepath) throws IOException {

        Map<String, String> data = new HashMap<>();
        String currentSection = "";
        Map<String, Integer> sectionCounters = new HashMap<>();

        try (Scanner scanner = new Scanner(new File(filepath))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (line.isEmpty()) continue;

                if (line.startsWith("[") && line.endsWith("]")) {
                    currentSection = line.substring(1, line.length() - 1).toLowerCase();
                    sectionCounters.put(currentSection, sectionCounters.getOrDefault(currentSection, -1) + 1);
                    continue;
                }

                int eqIndex = line.indexOf('=');
                if (eqIndex > 0) {
                    String key = line.substring(0, eqIndex).trim();
                    String value = line.substring(eqIndex + 1).trim();

                    if (!currentSection.isEmpty() && !currentSection.equals("mission")) {
                        int counter = sectionCounters.getOrDefault(currentSection, -1);
                        if (counter >= 0) {
                            key = currentSection + "[" + counter + "]." + key;
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
