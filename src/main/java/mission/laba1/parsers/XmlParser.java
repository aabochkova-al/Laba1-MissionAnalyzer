/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mission.laba1.parsers;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import mission.laba1.missionanalyzer.Mission;
import mission.laba1.missionanalyzer.MissionBuilder;

/**
 *
 * @author aleksandra
 */
public class XmlParser extends BasicParser{
    private XmlMapper mapper = new XmlMapper();
    
     @Override
    public Mission parse(String filepath) throws IOException{
       Map<String, Object> data = mapper.readValue(new File(filepath), Map.class);
       if (data.containsKey("mission")) {
            data = (Map<String, Object>) data.get("mission");
        }
       MissionBuilder builder = new MissionBuilder();
       fillPasrserFromBasic(builder, data);
       
       return builder.build();
    }
    
    private void printMap(Map<String, Object> map, int indent) {
        String spaces = "  ".repeat(indent);
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();

            if (value == null) {
                System.out.println(spaces + key + " = null");
            } else if (value instanceof Map) {
                System.out.println(spaces + key + " = {");
                printMap((Map<String, Object>) value, indent + 1);
                System.out.println(spaces + "}");
            } else if (value instanceof List) {
                System.out.println(spaces + key + " = [");
                List<?> list = (List<?>) value;
                for (int i = 0; i < list.size(); i++) {
                    Object item = list.get(i);
                    if (item instanceof Map) {
                        System.out.println(spaces + "  [" + i + "] = {");
                        printMap((Map<String, Object>) item, indent + 2);
                        System.out.println(spaces + "  }");
                    } else {
                        System.out.println(spaces + "  [" + i + "] = " + item);
                    }
                }
                System.out.println(spaces + "]");
            } else {
                System.out.println(spaces + key + " = " + value);
            }
        }
    }

    @Override
    public String getExtension() {
        return "xml";
    }
}
