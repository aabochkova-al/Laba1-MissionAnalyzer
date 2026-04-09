/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mission.laba1.reports;

import mission.laba1.missionanalyzer.EconomicAssessment;
import mission.laba1.missionanalyzer.EnvironmentConditions;
import mission.laba1.missionanalyzer.Mission;
import mission.laba1.missionanalyzer.Sorcer;
import mission.laba1.missionanalyzer.Technique;

/**
 *
 * @author aleksandra
 */
public class FullReport implements Formatter{

    @Override
    public void printReport(Mission mission) {
        System.out.println("Полный отчет: ");
        System.out.println("~~~ Основная информация ~~~");
        System.out.println("  ID миссии:    " + mission.getMissionId());
        System.out.println("  Дата:         " + mission.getDate());
        System.out.println("  Локация:      " + mission.getLocation());
        System.out.println("  Результат:    " + mission.getOutcome());
        if (mission.getDamageCost() != null) {
            System.out.println("  Ущерб:        " + mission.getDamageCost() + " йен");
        }
        System.out.println("~~~ Проклятие ~~~");
        if (mission.getCurse() != null) {
            System.out.println("  Название: " + mission.getCurse().getName());
            System.out.println("  Уровень:  " + mission.getCurse().getThreatLevel());
        }
        System.out.println("~~~ Участники ~~~");
        if (mission.getSorcerers() != null && !mission.getSorcerers().isEmpty()) {
            for (Sorcer s : mission.getSorcerers()) {
                System.out.println("  " + s.getName() + " (" + s.getRank() + ")");
            }
        }
        System.out.println("~~~ Техники ~~~");
        if (mission.getTechniques() != null && !mission.getTechniques().isEmpty()) {
            for (Technique t : mission.getTechniques()) {
                System.out.println("  " + t.getName() + " (" + t.getType() + ")");
                System.out.println("    Владелец: " + t.getOwner());
                if (t.getDamage() != null) {
                    System.out.println("    Урон: " + t.getDamage());
                }
            }
        }
        System.out.println("~~~ Экономическая оценка ~~~");
        if (mission.getEconomicAssessment() != null) {
            EconomicAssessment ea = mission.getEconomicAssessment();
            if (ea.getTotalDamageCost() != null)
                System.out.println("  Общий ущерб: " + ea.getTotalDamageCost() + " йен");
            if (ea.getRecoveryEstimateDays() != null)
                System.out.println("  Восстановление: " + ea.getRecoveryEstimateDays() + " дней");
        }
        System.out.println("~~~ Условия среды ~~~");
        if (mission.getEnvironment() != null) {
            EnvironmentConditions env = mission.getEnvironment();
            if (env.getWeather() != null)
                System.out.println("  Погода: " + env.getWeather());
            if (env.getTimeOfDay() != null)
                System.out.println("  Время суток: " + env.getTimeOfDay());
            if (env.getVisibility() != null)
                System.out.println("  Видимость: " + env.getVisibility());
        }

    }
    

    @Override
    public String getName() {
        return "full";
    }
    
}
