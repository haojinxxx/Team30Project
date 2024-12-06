package TestGrupp.Observer;

import TestGrupp.Model.GameObjectDTO;
import java.util.List;
import java.util.Observable;

public interface Observer {
    void update(List<GameObjectDTO> gameObjectDTOs);
}
