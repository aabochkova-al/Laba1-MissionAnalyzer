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
    protected Map<String, Object> parseToMap(String filepath) throws IOException {
        Yaml yaml = new Yaml();
        try (InputStream input = new FileInputStream(filepath)) {
            return yaml.load(input);
        }
    }

    @Override
    public String getExtension() {
        return "yaml";
    }
}