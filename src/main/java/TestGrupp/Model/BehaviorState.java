package TestGrupp.Model;

import TestGrupp.Model.TransformComponent;
import TestGrupp.Model.PhysicsComponent;
import TestGrupp.Model.Behaviors.AttackState;
public interface BehaviorState {
    void update(TransformComponent transform, PhysicsComponent physics, double deltaTime);
}