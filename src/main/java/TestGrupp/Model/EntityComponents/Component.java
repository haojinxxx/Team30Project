package TestGrupp.Model.EntityComponents;

import TestGrupp.Model.PhysicsComponent;
import TestGrupp.Model.TransformComponent;

import javax.vecmath.Point2d;

public interface Component {
    void update(double deltaTime, TransformComponent transform, PhysicsComponent physics, Point2d playerPosition);
}
