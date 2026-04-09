/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mission.laba1.reports;

import mission.laba1.missionanalyzer.Mission;

/**
 *
 * @author aleksandra
 */
public class UsualReport implements Formatter{

    @Override
    public void printReport(Mission mission) {
        System.out.println("Краткий отчет: ");
        System.out.println("  ID:     " + mission.getMissionId());
        System.out.println("  Дата:   " + mission.getDate());
        System.out.println("  Место:  " + mission.getLocation());
        System.out.println("  Статус: " + mission.getOutcome());
        
        if (mission.getCurse() != null) {
            System.out.println("  Угроза: " + mission.getCurse().getThreatLevel());
        }
        
        int sorcerersCount = mission.getSorcerers() != null ? mission.getSorcerers().size() : 0;
        int techniquesCount = mission.getTechniques() != null ? mission.getTechniques().size() : 0;
        System.out.println("  Магов:  " + sorcerersCount);
        System.out.println("  Техник: " + techniquesCount);
    }
    

    @Override
    public String getName() {
     return "usual";
    }
}   


