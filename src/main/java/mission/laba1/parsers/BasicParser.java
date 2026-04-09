/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mission.laba1.parsers;

import java.util.ArrayList;
import java.util.HashMap;
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
        builder.setMissionId(getStringValue(data.get("missionId")))
               .setDate(getStringValue(data.get("date")))
               .setLocation(getStringValue(data.get("location")))
               .setOutcome(getStringValue(data.get("outcome")));
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
            Object cost = data.get("damageCost");
            if (cost instanceof Integer) {
                builder.setDamageCost((Integer) cost);
            } else if (cost instanceof String) {
                try {
                    builder.setDamageCost(Integer.parseInt((String) cost));
                } catch (NumberFormatException e) {
                    System.out.println("Ошибка парсинга damageCost: " + cost);
                }
            }
        }
        
       //участники - универсальная версия
        if (data.containsKey("sorcerers")) {
            Object sorcerersObj = data.get("sorcerers");
            List<Map<String, String>> sorcerersList = new ArrayList<>();

            if (sorcerersObj instanceof List) {
                // Случай: sorcerers - это массив (JSON, YAML, TXT)
                sorcerersList = (List<Map<String, String>>) sorcerersObj;
            } 
            else if (sorcerersObj instanceof Map) {
                Map<String, Object> sorcerersMap = (Map<String, Object>) sorcerersObj;

                // Проверяем, есть ли ключ "sorcerer" (XML)
                if (sorcerersMap.containsKey("sorcerer")) {
                    Object sorcererObj = sorcerersMap.get("sorcerer");
                    if (sorcererObj instanceof List) {
                        sorcerersList = (List<Map<String, String>>) sorcererObj;
                    } else if (sorcererObj instanceof Map) {
                        sorcerersList.add((Map<String, String>) sorcererObj);
                    }
                }
                // Если нет ключа "sorcerer", возможно это сам объект мага (одиночный JSON/YAML)
                else if (sorcerersMap.containsKey("name") && sorcerersMap.containsKey("rank")) {
                    // Приводим Map<String, Object> к Map<String, String>
                    Map<String, String> singleSorcerer = new HashMap<>();
                    singleSorcerer.put("name", getStringValue(sorcerersMap.get("name")));
                    singleSorcerer.put("rank", getStringValue(sorcerersMap.get("rank")));
                    sorcerersList.add(singleSorcerer);
                }
            }

            for (Map<String, String> s : sorcerersList) {
                Sorcer sorcer = new Sorcer();
                sorcer.setName(s.get("name"));
                sorcer.setRank(s.get("rank"));
                builder.addSorcerer(sorcer);
            }
        }
        
       //техники - универсальная версия
        if (data.containsKey("techniques")) {
            Object techniquesObj = data.get("techniques");
            List<Map<String, Object>> techniquesList = new ArrayList<>();

            if (techniquesObj instanceof List) {
                techniquesList = (List<Map<String, Object>>) techniquesObj;
            }
            else if (techniquesObj instanceof Map) {
                Map<String, Object> techniquesMap = (Map<String, Object>) techniquesObj;

                if (techniquesMap.containsKey("technique")) {
                    Object techniqueObj = techniquesMap.get("technique");
                    if (techniqueObj instanceof List) {
                        techniquesList = (List<Map<String, Object>>) techniqueObj;
                    } else if (techniqueObj instanceof Map) {
                        techniquesList.add((Map<String, Object>) techniqueObj);
                    }
                }
                else if (techniquesMap.containsKey("name")) {
                    techniquesList.add(techniquesMap);
                }
            }

            for (Map<String, Object> t : techniquesList) {
                Technique tech = new Technique();
                tech.setName(getStringValue(t.get("name")));
                tech.setType(getStringValue(t.get("type")));
                tech.setOwner(getStringValue(t.get("owner")));

                if (t.containsKey("damage")) {
                    Object damageObj = t.get("damage");
                    if (damageObj instanceof Number) {
                        tech.setDamage(((Number) damageObj).intValue());
                    } else if (damageObj instanceof String) {
                        try {
                            tech.setDamage(Integer.parseInt((String) damageObj));
                        } catch (NumberFormatException e) {
                            System.out.println("Ошибка парсинга damage: " + damageObj);
                        }
                    }
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
            if (envData.containsKey("cursedEnergyDensity")) {
                Object density = envData.get("cursedEnergyDensity");
                if (density instanceof Integer) {
                    env.setCursedEnergyDensity((Integer) density);
                } else if (density instanceof String) {
                    try {
                        env.setCursedEnergyDensity(Integer.parseInt((String) density));
                    } catch (NumberFormatException e) {}
                }
            }
            builder.setEnvironment(env);
        }
    }
    
    protected String getStringValue(Object obj) {
        if (obj == null) return null;
        return obj.toString();
    }
}
