package TestGrupp.Observer;

import TestGrupp.Model.GameObjectDTO;
import java.util.List;

public interface Observer {
    void update(List<GameObjectDTO> gameObjectDTOs);
}
