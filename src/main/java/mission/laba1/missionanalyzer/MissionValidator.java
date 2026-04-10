/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mission.laba1.missionanalyzer;

import java.util.ArrayList;
import java.util.List;
import mission.laba1.ENUM.Outcome;

/**
 *
 * @author aleksandra
 */
public class MissionValidator {
     private boolean filterInvalidTechniques = true;
    
    public MissionValidator() {}
    
    public MissionValidator(boolean filterInvalidTechniques) {
        this.filterInvalidTechniques = filterInvalidTechniques;
    }
    
    public void validate(Mission mission) throws IllegalStateException {
        // Обязательные поля
        requireNonNull(mission.getMissionId(), "missionId обязателен!");
        requireNonNull(mission.getDate(), "date обязателен!");
        requireNonNull(mission.getLocation(), "location обязателен!");
        
        if (mission.getOutcome() == null || mission.getOutcome() == Outcome.UNKNOWN) {
            throw new IllegalStateException("outcome обязателен!");
        }
        
        if (mission.getCurse() == null) {
            throw new IllegalStateException("curse обязателен!");
        }
        
        // Техники требуют магов
        if (hasTechniques(mission) && !hasSorcerers(mission)) {
            throw new IllegalStateException("Миссия содержит техники, но не содержит магов!");
        }
        
        // Фильтрация техник с ненайденными владельцами
        if (filterInvalidTechniques && hasTechniques(mission) && hasSorcerers(mission)) {
            filterTechniquesByOwners(mission);
        }
    }
    
    private void requireNonNull(Object value, String message) {
        if (value == null || (value instanceof String && ((String) value).isEmpty())) {
            throw new IllegalStateException(message);
        }
    }
    
    private boolean hasTechniques(Mission mission) {
        return mission.getTechniques() != null && !mission.getTechniques().isEmpty();
    }
    
    private boolean hasSorcerers(Mission mission) {
        return mission.getSorcerers() != null && !mission.getSorcerers().isEmpty();
    }
    
    private void filterTechniquesByOwners(Mission mission) {
        List<Technique> validTechniques = new ArrayList<>();
        List<String> errors = new ArrayList<>();
        
        for (Technique t : mission.getTechniques()) {
            boolean ownerFound = false;
            for (Sorcer s : mission.getSorcerers()) {
                if (s.getName().equals(t.getOwner())) {
                    ownerFound = true;
                    break;
                }
            }
            if (ownerFound) {
                validTechniques.add(t);
            } else {
                errors.add("Техника '" + t.getName() + "' (владелец: " + t.getOwner() + ") пропущена - владелец не найден");
            }
        }
        
        mission.setTechniques(validTechniques);
        
        if (!errors.isEmpty()) {
            System.out.println("\nПредупреждение: ");
            for (String error : errors) {
                System.out.println("  - " + error);
            }
            System.out.println();
        }
    }
}
