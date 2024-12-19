package TestGrupp.Model;

import java.awt.geom.Rectangle2D;
import java.util.List;

public class CollisionManager {
    public CollisionManager(List<GameObject> collidibleObjects) {

    }

    public void update(List<GameObject> collidibleObjects) {
        for (int i = 0; i < collidibleObjects.size(); i++) {
            GameObject obj1 = collidibleObjects.get(i);
            System.out.printf("print bool object is asteroid %b\n", obj1 instanceof Asteroid);
            // Skip projectiles as collision "owners" as we dont consider projectiles "collision owners"
            if (obj1 instanceof Projectile) {
                continue;
            }

            // Check collisions with other objects and projectiles
            for (int j = 0; j < collidibleObjects.size(); j++) {
                if (i == j) {
                    continue; // Avoid self-collision
                }

                GameObject obj2 = collidibleObjects.get(j);


                if (checkCollision(obj1, obj2)) {
                    handleCollision(obj1, obj2); // Handle collision
                }
            }
        }
    }

    private boolean checkCollision(GameObject obj1, GameObject obj2) {
        Rectangle2D.Float box1 = obj1.getTransform().getBoundingBox();
        Rectangle2D.Float box2 = obj2.getTransform().getBoundingBox();
        return box1.intersects(box2);
    }



    private static void handleCollision(GameObject owner, GameObject other) {

        // If the owner is a PlayerShip
        System.out.printf("Received collision between type %s and type %s\n", owner.getClass().getSimpleName(), other.getClass().getSimpleName());
        owner.onCollision(other);
        if (other instanceof Projectile) { // As projectiles are not collision owners, we need to handle the collision from the projectiles perspective also
            other.onCollision(owner);
        }
    }
}