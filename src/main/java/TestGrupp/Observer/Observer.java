package TestGrupp.Observer;

import TestGrupp.Model.GameObjectDTO;
import TestGrupp.Model.PowerUp;

import java.util.List;
import java.util.Map;
import java.util.Observable;

public interface Observer {
    void update(List<GameObjectDTO> gameObjectDTOs);
    void updateScore(int score);
    void updateHealth(int health); // New method for health updates
}
    void updatePowerUps(List<PowerUp> collectedPowerUps);
}
