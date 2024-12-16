package TestGrupp.Model.EntityComponents;


import TestGrupp.Model.PhysicsComponent;
import TestGrupp.Model.TransformComponent;

import javax.vecmath.Point2d;
import javax.vecmath.Vector2d;

public class FacePlayer implements Component{
    public void update(double deltaTime, TransformComponent transform, PhysicsComponent physics, Point2d playerPosition) {
        // Get vector to player
        Vector2d directionToPlayer = new Vector2d(
                playerPosition.getX() - transform.getPosition().getX(),
                playerPosition.getY() - transform.getPosition().getY()
        );
        directionToPlayer.normalize();

        // Face player
        double rotation = Math.atan2(directionToPlayer.y, directionToPlayer.x);
        transform.setRotation(rotation);
    }
}