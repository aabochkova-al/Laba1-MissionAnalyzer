/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mission.laba1.facade;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import mission.laba1.missionanalyzer.Mission;
import mission.laba1.missionanalyzer.MissionBuilder;
import mission.laba1.missionanalyzer.MissionValidator;
import mission.laba1.parsers.MissionParser;
import mission.laba1.parsers.ParserFactory;
import mission.laba1.reports.Formatter;
import mission.laba1.reports.FullReport;
import mission.laba1.reports.UsualReport;

/**
 *
 * @author aleksandra
 */
public class Facade {
    private Map<String, Formatter> formatters = new HashMap<>();
    private String defaultFormatName;
    
    public Facade(){
        ParserFactory.setup();
        formatters.put("full", new FullReport());
        formatters.put("usual", new UsualReport());
        defaultFormatName = "full";
    }
    
    // Добавление нового формата
    public void addFormatter(Formatter formatter) {
        formatters.put(formatter.getName(), formatter);
    }
    
    public void setDefaultFormat(String formatName) {
        if (formatters.containsKey(formatName)) {
            defaultFormatName = formatName;
            System.out.println("Формат отчета изменен на: " + formatName);
        } else {
            System.out.println("Формат '" + formatName + "' не найден. Доступны: " + getAvailableFormats());
        }
    }
    
    public Mission analyzeMission(String filepath) throws IOException {
        MissionParser parser = ParserFactory.getParser(filepath);
        MissionBuilder builder = new MissionBuilder();
        
        System.out.println("Парсер: " + parser.getClass().getSimpleName());
        
        Mission mission = parser.parse(builder, filepath);
        new MissionValidator().validate(mission);
        
        return mission;
    }
    
    public List<Mission> analyzeFolder(String folderPath) {
        List<Mission> missions = new ArrayList<>();
        File folder = new File(folderPath);

        if (!folder.exists() || !folder.isDirectory()) {
            System.out.println("Папка не найдена: " + folderPath);
            return missions;
        }

        File[] files = folder.listFiles();
        if (files == null) return missions;

        System.out.println("\nСканирование папки...");
        int fileCount = 0;

        for (File file : files) {
            if (file.isFile()) {
                fileCount++;
                try {
                    Mission mission = analyzeMission(file.getAbsolutePath());
                    missions.add(mission);
                } catch (Exception e) {}
            }
        }
        return missions;
    }

    
    public void printReport(Mission mission) {
        printReport(mission, defaultFormatName);
    }
    
    public void printReport(Mission mission, String formatName) {
        if (mission == null) {
            System.out.println("Нет данных для отображения");
            return;
        }
        
        Formatter formatter = formatters.get(formatName);
        if (formatter == null) {
            System.out.println("Формат '" + formatName + "' не найден. Использую '" + defaultFormatName + "'");
            formatter = formatters.get(defaultFormatName);
        }
        
        formatter.printReport(mission);
    }
    
    public List<String> getAvailableFormats() {
        return new ArrayList<>(formatters.keySet());
    }
}
