/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mission.laba1.parsers;

import java.util.List;
import java.util.Map;
import mission.laba1.missionanalyzer.Curse;
import mission.laba1.missionanalyzer.EconomicAssessment;
import mission.laba1.missionanalyzer.EnvironmentConditions;
import mission.laba1.missionanalyzer.MissionBuilder;
import mission.laba1.missionanalyzer.Sorcer;
import mission.laba1.missionanalyzer.Technique;

/**
 *
 * @author aleksandra
 */
public abstract class BasicParser implements MissionParser{
    protected void fillPasrserFromBasic(MissionBuilder builder, Map<String, Object> data) {
        //обязательные поля
       builder.setMissionId((String) data.get("missionId"))
               .setDate((String) data.get("date"))
               .setLocation((String) data.get("location"))
               .setOutcome((String) data.get("outcome"));
       //проклятие
       if(data.containsKey("curse")){
           Map<String, String> curseData = (Map) data.get("curse");
           Curse curse = new Curse();
           curse.setName(curseData.get("name"));
           curse.setThreatLevel(curseData.get("threatLevel"));
           builder.setCurse(curse);
       }
       //damageCost
        if (data.containsKey("damageCost")) {
            builder.setDamageCost((Integer) data.get("damageCost"));
        }
        
       //yчастники
        if (data.containsKey("sorcerers")) {
            List<Map<String, String>> list = (List) data.get("sorcerers");
            for (Map<String, String> s : list) {
                Sorcer sorcer = new Sorcer();
                sorcer.setName(s.get("name"));
                sorcer.setRank(s.get("rank"));
                builder.addSorcerer(sorcer);
            }
        }
        
       //техники
        if (data.containsKey("techniques")) {
            List<Map<String, Object>> list = (List) data.get("techniques");
            for (Map<String, Object> t : list) {
                Technique tech = new Technique();
                tech.setName((String) t.get("name"));
                tech.setType((String) t.get("type"));
                tech.setOwner((String) t.get("owner"));
                if (t.containsKey("damage")) {
                    tech.setDamage((Integer) t.get("damage"));
                }
                builder.addTechnique(tech);
            }
        }
        
       //экономическая оценка
        if (data.containsKey("economicAssessment")) {
            Map<String, Object> econ = (Map) data.get("economicAssessment");
            EconomicAssessment assessment = new EconomicAssessment();
            if (econ.containsKey("totalDamageCost"))
                assessment.setTotalDamageCost((Integer) econ.get("totalDamageCost"));
            if (econ.containsKey("infrastructureDamage"))
                assessment.setInfrastructureDamage((Integer) econ.get("infrastructureDamage"));
            if (econ.containsKey("commercialDamage"))
                assessment.setCommercialDamage((Integer) econ.get("commercialDamage"));
            if (econ.containsKey("transportDamage"))
                assessment.setTransportDamage((Integer) econ.get("transportDamage"));
            if (econ.containsKey("recoveryEstimateDays"))
                assessment.setRecoveryEstimateDays((Integer) econ.get("recoveryEstimateDays"));
            if (econ.containsKey("insuranceCovered"))
                assessment.setInsuranceCovered((Boolean) econ.get("insuranceCovered"));
            builder.setEconomicAssessment(assessment);
        }
        
        //условия среды
        if (data.containsKey("environment")) {
            Map<String, Object> envData = (Map) data.get("environment");
            EnvironmentConditions env = new EnvironmentConditions();
            
            if (envData.containsKey("weather"))
                env.setWeather((String) envData.get("weather"));
            if (envData.containsKey("timeOfDay"))
                env.setTimeOfDay((String) envData.get("timeOfDay"));
            if (envData.containsKey("visibility"))
                env.setVisibility((String) envData.get("visibility"));
            if (envData.containsKey("cursedEnergyDensity"))
                env.setCursedEnergyDensity((Integer) envData.get("cursedEnergyDensity"));
    
            builder.setEnvironment(env);
        }
    }
}
