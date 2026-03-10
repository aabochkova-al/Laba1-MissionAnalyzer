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
import mission.laba1.missionanalyzer.Sorcer;
import mission.laba1.missionanalyzer.Technique;

/**
 *
 * @author aleksandra
 */
public class TxtParser implements MissionParser{

    @Override
    public Mission parse(String filepath) throws IOException {
        Map<String,String> data = fileToMapEdit(filepath);
        
        Mission mission = new Mission();
        
        mission.setMissionId(data.get("missionId"));
        mission.setDate(data.get("date"));
        mission.setLocation(data.get("location"));
        mission.setOutcome(data.get("outcome"));
        
        String dmgStr = data.get("damageCost");
        if(dmgStr != null){
            mission.setDamageCost(Integer.parseInt(dmgStr));
        }
        Curse curse = new Curse();
        curse.setName(data.get("curse.name"));
        curse.setThreatLevel(data.get("curse.threatLevel"));
        mission.setCurse(curse);
        
        mission.setSorcerers(createSorcerers(data));
        mission.setTechniques(createTechniques(data));
        
        
        return mission;
    }
    
    
    private Map<String,String> fileToMapEdit(String filepath) throws IOException {
        Map<String,String> data = new HashMap<>();
        File file = new File(filepath);
        Scanner scanner = new Scanner(file);
        
        while(scanner.hasNextLine()){
            String line = scanner.nextLine().trim();
            if(line.isEmpty()) continue;
            
            int del = line.indexOf(':');
            if(del>0){
                String key = line.substring(0,del).trim();
                String value = line.substring(del + 1).trim();
                data.put(key, value);
            }
        }
        scanner.close();
        return data;
    }
    
    private List<Sorcer> createSorcerers(Map<String,String> data){
        List<Sorcer> sorcerers = new ArrayList<>();
        int index = 0;
        
        while(true){
            String name = data.get("sorcerer[" + index + "].name");
            if(name == null){
                break;
            }
            
            Sorcer s = new Sorcer();
            s.setName(name);
            s.setRank(data.get("sorcerer[" + index + "].rank"));
            sorcerers.add(s);
            index++;
        }
        return sorcerers;
    }
    
    private List<Technique> createTechniques(Map<String,String> data){
        List<Technique> techs = new ArrayList<>();
        int index = 0;
        
        while(true){
            String name = data.get("technique[" + index + "].name");
            if(name == null){
                break;
            }
            
            Technique t = new Technique();
            t.setName(name);
            t.setOwner(data.get("technique[" + index + "].owner"));
            t.setType(data.get("technique[" + index + "].type"));
            String dmgStr = data.get("technique[" + index + "].name");
            if(dmgStr != null){
                t.setDamage(Integer.parseInt(dmgStr));
            }
            techs.add(t);
            index++;
        }
        return techs;
    }
    
}
