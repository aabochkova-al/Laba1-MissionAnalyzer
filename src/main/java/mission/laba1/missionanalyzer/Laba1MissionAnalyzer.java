/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package mission.laba1.missionanalyzer;

import java.io.File;
import java.util.Scanner;
import mission.laba1.parsers.MissionParser;
import mission.laba1.parsers.ParserFactory;

/**
 *
 * @author aleksandra
 */
public class Laba1MissionAnalyzer {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Добро пожаловать в анализатор миссий!");
        
        while(true){
            System.out.println("\nВведите путь к файлу для анализа (или введите \"exit\" для выхода): ");
            String filepath = scanner.nextLine();

            if(filepath.equals("exit")){
                break;
            }

            File file = new File(filepath); 
            if(!file.exists()){
                System.out.println("Файл не найден");
                continue;
            }
            
            try{
                MissionParser parser = ParserFactory.getParser(filepath);
                if(parser == null){
                    System.out.println("Неподдерживаемый файл");
                    continue;
                }
                
                Mission m = parser.parse(filepath);
                
                System.out.println("\n--Информация по миссии--");
                System.out.println("ID: " + m.getMissionId());
                System.out.println("Дата: " + m.getDate());
                System.out.println("Место: " + m.getLocation());
                System.out.println("Результат: " + m.getOutcome());
                if(m.getDamageCost() != null){
                    System.out.println("Ущерб: " + m.getDamageCost() + "йен");
                }
                System.out.println("\n--- Цель ---");
                if (m.getCurse() != null) {
                    System.out.println("Проклятие: " + m.getCurse().getName());
                    System.out.println("Уровень: " + m.getCurse().getThreatLevel());
                }
                System.out.println("\n--- Участники ---");
                if (m.getSorcerers() != null) {
                    for (Sorcer s : m.getSorcerers()) {
                        System.out.println(s.getName() + " (" + s.getRank() + ")");
                    }
                }
                System.out.println("\n--- Техники ---");
                if (m.getTechniques() != null) {
                    for (Technique t : m.getTechniques()) {
                        System.out.println(t.getName() + " - тип: " + t.getType());
                        System.out.println("  Владелец: " + t.getOwner());
                        if (t.getDamage() != null) {
                            System.out.println("  Урон: " + t.getDamage());
                        }
                    }
                }
            }catch (Exception e){
                System.out.println("Ошибка: " + e.getMessage());
            }
        }
        scanner.close();
    }
}
