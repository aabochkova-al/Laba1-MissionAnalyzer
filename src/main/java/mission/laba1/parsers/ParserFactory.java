/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mission.laba1.parsers;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author aleksandra
 */
public class ParserFactory {
    private static Map<String, MissionParser> parsers = new HashMap<>();
    
    public static MissionParser getParser(String filepath) {
        // Берем только имя файла (без пути)
        File file = new File(filepath);
        String fileName = file.getName();

        int finalDot = fileName.lastIndexOf('.');

        if (finalDot > 0) {
            String ext = fileName.substring(finalDot + 1).toLowerCase();
            MissionParser parser = parsers.get(ext);
            if (parser != null) {
                return parser;
            }
        }
    
    // Если нет расширения или парсер не найден - используем парсер для файлов без расширения
    return parsers.get("none");
}
        
    public static void addParser(String extension, MissionParser parser) {
        parsers.put(extension, parser);
    }
    
    public static void setup() {
        parsers.put("json", new JsonParser());
        parsers.put("xml", new XmlParser());
        parsers.put("txt", new TxtParser());
        parsers.put("yaml", new YamlParser());
        parsers.put("none", new NoExtensionParser());
        
//        System.out.println("Зарегистрированные парсеры:");
//    for (Map.Entry<String, MissionParser> entry : parsers.entrySet()) {
//        System.out.println("  '" + entry.getKey() + "' -> " + entry.getValue().getClass().getSimpleName());
//    }
    }
   
}
