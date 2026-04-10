/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package mission.laba1.missionanalyzer;

import java.io.File;
import java.util.List;
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
            System.out.println("\nВведите путь: ");
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
                System.out.println("Путь не существует!");
                continue;
            }
            if (file.isFile()) {
                // Обрабатываем один файл
                processSingleFile(facade, file);
            } 
            else if (file.isDirectory()) {
                // Обрабатываем папку
                processFolder(facade, file, scanner);
            }

        } 
        System.out.println("\nРабота завершена!");
        scanner.close();
    }
        
    private static void processSingleFile(Facade facade, File file) {
        try {
            Mission mission = facade.analyzeMission(file.getAbsolutePath());
            System.out.println("Данные миссии валидны");
            facade.printReport(mission);
        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }
    
    private static void processFolder(Facade facade, File folder, Scanner scanner) {
        System.out.println("\nОбработка папки: " + folder.getName() );
        
        List<Mission> missions = facade.analyzeFolder(folder.getAbsolutePath());
        
        if (missions.isEmpty()) {
            System.out.println("В папке не найдено файлов для анализа");
            return;
        }
        for (int i = 0; i < missions.size(); i++) {
            System.out.println("\n--- Миссия " + (i + 1) + " ---");
            facade.printReport(missions.get(i));
        }
        
    }
    
}
