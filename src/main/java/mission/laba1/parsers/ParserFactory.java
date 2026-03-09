/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mission.laba1.parsers;

/**
 *
 * @author aleksandra
 */
public class ParserFactory {
    public static MissionParser getParser(String filepath){
        int finalDot = filepath.lastIndexOf('.');
        String ext = "";
        if(finalDot>0){
            ext = filepath.substring(finalDot + 1).toLowerCase();
        }
        
        if(ext.equals("json")){
            return new JsonParser();
        } else if (ext.equals("xml")){
            return new XmlParser();
        } else if (ext.equals("txt")){
            return new TxtParser();
        }
        
        return null;
    }
   
}
