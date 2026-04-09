/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mission.laba1.parsers;

import java.io.File;
import java.io.IOException;
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
public class NoExtensionParser implements MissionParser {
    @Override
    public Mission parse(String filepath) throws IOException {
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
    
    private Mission parseNoneFormat(String content) {
        MissionBuilder builder = new MissionBuilder();
        
        String[] lines = content.split("\n");
        
        for (String line : lines) {
            line = line.trim();
            if (line.isEmpty()) continue;

            String[] parts = line.split("\\|");
            if (parts.length == 0) continue;
            
            String type = parts[0];

            if (type.equals("MISSION_CREATED")) {
                builder.setMissionId(parts[1]);
                builder.setDate(parts[2]);
                builder.setLocation(parts[3]);
            }

            else if (type.equals("CURSE_DETECTED")) {
                Curse curse = new Curse();
                curse.setName(parts[1]);
                curse.setThreatLevel(parts[2]);
                builder.setCurse(curse);
            }

            else if (type.equals("SORCERER_ASSIGNED")) {
                Sorcer sorcer = new Sorcer();
                sorcer.setName(parts[1]);
                sorcer.setRank(parts[2]);
                builder.addSorcerer(sorcer);
            }
            
            else if (type.equals("TECHNIQUE_USED")) {
                Technique tech = new Technique();
                tech.setName(parts[1]);
                tech.setType(parts[2]);
                tech.setOwner(parts[3]);
                if (parts.length >= 5 && !parts[4].isEmpty()) {
                    try {
                        tech.setDamage(Integer.parseInt(parts[4]));
                    } catch (NumberFormatException e) {
                        System.out.println("Не число");
                    }
                }
                builder.addTechnique(tech);
            }
            
            else if (type.equals("MISSION_RESULT")) {
                builder.setOutcome(parts[1]);
                for (int i = 2; i < parts.length; i++) {
                    if (parts[i].startsWith("damageCost=")) {
                        String costStr = parts[i].substring(11);
                        try {
                            builder.setDamageCost(Integer.parseInt(costStr));
                        } catch (NumberFormatException e) {}
                    }
                }
            }
        }
        
        return builder.build();
    }

    @Override
    public String getExtension() {
     return "";    
    }
}
