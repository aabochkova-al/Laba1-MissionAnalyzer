package mission.laba1.parsers;


import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import mission.laba1.missionanalyzer.Mission;
import mission.laba1.missionanalyzer.MissionBuilder;
import mission.laba1.parsers.BasicParser;
import org.yaml.snakeyaml.Yaml;


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author aleksandra
 */
public class YamlParser extends BasicParser{

    @Override
    public Mission parse(String filepath) throws IOException {
        
        Yaml yaml = new Yaml();
        try (InputStream input = new FileInputStream(filepath)) {
            Map<String, Object> data = yaml.load(input);
            MissionBuilder builder = new MissionBuilder();
            fillPasrserFromBasic(builder, data);
            return builder.build();
        }
    }


    @Override
    public String getExtension() {
        return "yaml";
    }
}