/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package mission.laba1.missionanalyzer;

import java.io.File;
import java.util.Scanner;
import mission.laba1.facade.Facade;
import mission.laba1.parsers.MissionParser;
import mission.laba1.parsers.ParserFactory;

/**
 *
 * @author aleksandra
 */
public class Laba1MissionAnalyzer {
    

    public static void main(String[] args) {
        ParserFactory.setup();
        Facade facade = new Facade();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Добро пожаловать в анализатор миссий!");
        
        System.out.println("\nКоманды:");
        System.out.println("  format:full - переключиться на полный отчет");
        System.out.println("  format:usual - переключиться на краткий отчет");
        System.out.println("  exit - выход");
        

        while(true){
            System.out.println("\nВведите путь к файлу для анализа: ");
            String filepath = scanner.nextLine().trim();

            if(filepath.equals("exit")){
                break;
            }
            
            if (filepath.startsWith("format:")) {
                String formatName = filepath.substring(7);
                facade.setDefaultFormat(formatName);
                continue;
            }

            File file = new File(filepath); 
            if(!file.exists()){
                System.out.println("Файл не найден");
                continue;
            }
            
           try {
                Mission mission = facade.analyzeMission(filepath);
                System.out.println("Данные миссии валидны");
                facade.printReport(mission);
            } catch (Exception e) {
                System.out.println("Ошибка: " + e.getMessage());
            }
        }
        
        System.out.println("\nРабота завершена!");
        scanner.close();
    }
    
}
