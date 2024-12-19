// src/main/java/TestGrupp/Observer/Subject.java
package TestGrupp.Observer;

import TestGrupp.Model.GameObjectDTO;

import TestGrupp.Model.PowerUp;

import java.util.List;

public interface Subject {
    void addObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers(List<GameObjectDTO> gameObjectDTOs, int score, List<PowerUp> collectedPowerUps);
}