/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mission.laba1.missionanalyzer;

import mission.laba1.ENUM.TimeOfDay;
import mission.laba1.ENUM.Visibility;
import mission.laba1.ENUM.Weather;

/**
 *
 * @author aleksandra
 */
public class EnvironmentConditions {
    private Weather weather;
    private TimeOfDay timeOfDay;
    private Visibility visibility;
    private Integer cursedEnergyDensity;
    
    public Weather getWeather() { return weather; }
    public void setWeather(Weather weather) { this.weather = weather; }
    public void setWeather(String weather) { this.weather = Weather.fromString(weather); }
    
    public TimeOfDay getTimeOfDay() { return timeOfDay; }
    public void setTimeOfDay(TimeOfDay timeOfDay) { this.timeOfDay = timeOfDay; }
    public void setTimeOfDay(String timeOfDay) { this.timeOfDay = TimeOfDay.fromString(timeOfDay); }
    
    public Visibility getVisibility() { return visibility; }
    public void setVisibility(Visibility visibility) { this.visibility = visibility; }
    public void setVisibility(String visibility) { this.visibility = Visibility.fromString(visibility); }
    
    public Integer getCursedEnergyDensity() { return cursedEnergyDensity; }
    public void setCursedEnergyDensity(Integer cursedEnergyDensity) { this.cursedEnergyDensity = cursedEnergyDensity; }
}

