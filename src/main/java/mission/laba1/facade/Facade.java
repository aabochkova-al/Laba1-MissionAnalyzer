/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mission.laba1.facade;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import mission.laba1.missionanalyzer.Mission;
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
        if (parser == null) {
            throw new IOException("Неподдерживаемый формат файла: " + filepath);
        }
        
        System.out.println("Парсер: " + parser.getClass().getSimpleName());
        
        Mission mission = parser.parse(filepath);
        mission.validateOrThrow();
        
        return mission;
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
