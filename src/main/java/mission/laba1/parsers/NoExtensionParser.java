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
public class NoExtensionParser extends BasicParser {
    @Override
    protected Map<String, Object> parseToMap(String filepath) throws IOException {
        Scanner scanner = new Scanner(new File(filepath));
        StringBuilder sb = new StringBuilder();
        while (scanner.hasNextLine()) {
            sb.append(scanner.nextLine()).append("\n");
        }
        scanner.close();
        
        String content = sb.toString();

        if (content.contains("|") && content.contains("MISSION_CREATED")) {
            return parseNoneFormat(content);
        }
        
        throw new IOException("Неизвестный формат файла: " + filepath);
    }
    
     private Map<String, Object> parseNoneFormat(String content) {
        Map<String, Object> result = new HashMap<>();
        List<Map<String, String>> sorcerersList = new ArrayList<>();
        List<Map<String, Object>> techniquesList = new ArrayList<>();
        
        String[] lines = content.split("\n");
        
        for (String line : lines) {
            line = line.trim();
            if (line.isEmpty()) continue;

            String[] parts = line.split("\\|");
            if (parts.length == 0) continue;
            
            String type = parts[0];

            if (type.equals("MISSION_CREATED")) {
                result.put("missionId", parts[1]);
                result.put("date", parts[2]);
                result.put("location", parts[3]);
            }

            else if (type.equals("CURSE_DETECTED")) {
                Map<String, String> curseData = new HashMap<>();
                curseData.put("name", parts[1]);
                curseData.put("threatLevel", parts[2]);
                result.put("curse", curseData);
            }

            else if (type.equals("SORCERER_ASSIGNED")) {
                Map<String, String> sorcererData = new HashMap<>();
                sorcererData.put("name", parts[1]);
                sorcererData.put("rank", parts[2]);
                sorcerersList.add(sorcererData);
            }
            
            else if (type.equals("TECHNIQUE_USED")) {
                Map<String, Object> techData = new HashMap<>();
                techData.put("name", parts[1]);
                techData.put("type", parts[2]);
                techData.put("owner", parts[3]);
                if (parts.length >= 5 && !parts[4].isEmpty()) {
                    try {
                        techData.put("damage", Integer.parseInt(parts[4]));
                    } catch (NumberFormatException e) {}
                }
                techniquesList.add(techData);
            }
            
            else if (type.equals("MISSION_RESULT")) {
                result.put("outcome", parts[1]);
                for (int i = 2; i < parts.length; i++) {
                    if (parts[i].startsWith("damageCost=")) {
                        String costStr = parts[i].substring(11);
                        try {
                           result.put("damageCost", Integer.parseInt(costStr));
                        } catch (NumberFormatException e) {}
                    }
                }
            }
        }
        
        if (!sorcerersList.isEmpty()) {
            result.put("sorcerers", sorcerersList);
        }
        if (!techniquesList.isEmpty()) {
            result.put("techniques", techniquesList);
        }
        
        return result;
    }

    @Override
    public String getExtension() {
     return "none";    
    }
}
