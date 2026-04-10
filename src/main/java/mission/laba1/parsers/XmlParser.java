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
    protected Map<String, Object> parseToMap(String filepath) throws IOException{
       Map<String, Object> data = mapper.readValue(new File(filepath), Map.class);
       if (data.containsKey("mission")) {
            data = (Map<String, Object>) data.get("mission");
        }
       return data;
    }

    @Override
    public String getExtension() {
        return "xml";
    }
}
