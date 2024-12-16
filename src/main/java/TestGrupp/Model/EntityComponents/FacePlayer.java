package TestGrupp.Model.EntityComponents;


import TestGrupp.Model.PhysicsComponent;
import TestGrupp.Model.TransformComponent;

import javax.vecmath.Point2d;
import javax.vecmath.Vector2d;

public class FacePlayer implements Component{



    /**
 * Updates the component to face the player by adjusting the rotation of the transform component.
 *
 * @param deltaTime     The time elapsed since the last update, in seconds.
 * @param transform     The transform component of the entity.
 * @param physics       The physics component of the entity.
 * @param playerPosition The position of the player.
 */
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