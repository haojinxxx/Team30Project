package TestGrupp.Model;

import javax.vecmath.Point2d;


public class EnemyFactory {

    public GameObject createEnemy(String type, GameEventListener gameEventListener) {
        switch (type) {
            case "Asteroid":
                double randomRotation = Math.random() * 360;
                Asteroid asteroid = new Asteroid(new Point2d(), randomRotation,600,1,2,gameEventListener);
                return asteroid;
            case "EnemyShip":
                EnemyShip enemyShip = new EnemyShip(new Point2d(), 0, 500, 10, 0, 400, gameEventListener);
                return enemyShip;
            default:
                throw new IllegalArgumentException("Invalid enemy type: " + type);
        }

    }
}
