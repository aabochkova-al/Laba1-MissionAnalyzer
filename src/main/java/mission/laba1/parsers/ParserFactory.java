/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mission.laba1.parsers;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author aleksandra
 */
public class ParserFactory {
    private static Map<String, MissionParser> parsers = new HashMap<>();
    
    public static MissionParser getParser(String filepath){
        int finalDot = filepath.lastIndexOf('.');
        String ext = "";
        if(finalDot>0){
            ext = filepath.substring(finalDot + 1).toLowerCase();
        }
        if (finalDot == -1) {
            // Нет расширения
            return parsers.get("");
        }
        return parsers.get(ext);
    }
        
    public static void addParser(String extension, MissionParser parser) {
        parsers.put(extension, parser);
    }
    
    public static void setup() {
        parsers.put("json", new JsonParser());
        parsers.put("xml", new XmlParser());
        parsers.put("txt", new TxtParser());
        parsers.put("yaml", new YamlParser());
        parsers.put("", new NoExtensionParser());
    }
   
}
