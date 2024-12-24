package TestGrupp.Model;

import TestGrupp.Controller.ConfigurationLoader;

import javax.vecmath.Point2d;


public class EnemyFactory {
    int enemyCooldown = ConfigurationLoader.getProperty("EnemyShip.cooldown");
    int enemyFiringRange = ConfigurationLoader.getProperty("EnemyShip.range");
    int enemyDamage = ConfigurationLoader.getProperty("EnemyShip.damage");
    int enemyHealth = ConfigurationLoader.getProperty("EnemyShip.health");
    int enemySpeed = ConfigurationLoader.getProperty("EnemyShip.speed");

    int asteroidHealth = ConfigurationLoader.getProperty("Asteroid.health");
    int asteroidSpeed = ConfigurationLoader.getProperty("Asteroid.speed");

    public GameObject createEnemy(String type, GameEventListener gameEventListener) {
        switch (type) {
            case "Asteroid":
                double randomRotation = Math.random() * 360;
                Asteroid asteroid = new Asteroid(new Point2d(), randomRotation,asteroidSpeed,asteroidHealth,2,gameEventListener);
                return asteroid;
            case "EnemyShip":
                EnemyShip enemyShip = new EnemyShip(new Point2d(), 0, enemySpeed, enemyHealth, enemyDamage, enemyFiringRange, gameEventListener, enemyCooldown);
                return enemyShip;
            default:
                throw new IllegalArgumentException("Invalid enemy type: " + type);
        }

    }
}
