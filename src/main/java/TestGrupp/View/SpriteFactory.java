package TestGrupp.View;

import TestGrupp.Controller.ConfigurationLoader;

public class SpriteFactory {


    public SpriteFactory() {

    }

    public Sprite createSprite(String spriteType) {
        System.out.println("createSprite called with spriteType: " + spriteType);
        switch (spriteType) {
            case "PlayerShip":
                System.out.println("Creating PlayerShip sprite...");
                PlayerShipSprite playerShipSprite = new PlayerShipSprite(ConfigurationLoader.getProperty("player.width"), ConfigurationLoader.getProperty("player.height"));
                playerShipSprite.setBounds(0, 0, ConfigurationLoader.getProperty("player.width"), ConfigurationLoader.getProperty("player.height")); // Set bounds explicitly
                return playerShipSprite;
            case "PlayerProjectile":
                System.out.println("Creating PlayerProjectile sprite...");
                PlayerProjectileSprite playerProjectileSprite = new PlayerProjectileSprite(ConfigurationLoader.getProperty("projectile.width"), ConfigurationLoader.getProperty("projectile.height"));
                playerProjectileSprite.setBounds(0, 0, ConfigurationLoader.getProperty("projectile.width"), ConfigurationLoader.getProperty("projectile.height")); // Set bounds explicitly
                return playerProjectileSprite;
            case "PowerUp":
                System.out.println("Creating PowerUp sprite...");
                PowerUpSprite powerUpSprite = new PowerUpSprite(ConfigurationLoader.getProperty("powerup.width"), ConfigurationLoader.getProperty("powerup.height"));
                powerUpSprite.setBounds(0, 0,ConfigurationLoader.getProperty("powerup.width"), ConfigurationLoader.getProperty("powerup.height")); // Set bounds explicitly
                return powerUpSprite;
            case "Asteroid":
                System.out.println("Creating Asteroid sprite...");
                AsteroidSprite asteroidSprite = new AsteroidSprite(ConfigurationLoader.getProperty("asteroid.width"), ConfigurationLoader.getProperty("asteroid.height"));
                asteroidSprite.setBounds(0, 0, ConfigurationLoader.getProperty("asteroid.width"), ConfigurationLoader.getProperty("asteroid.height")); // Set bounds explicitly
                return asteroidSprite;

            case "EnemyShip":
                System.out.println("Creating EnemyShip sprite...");
                EnemyShipSprite enemyShip = new EnemyShipSprite(ConfigurationLoader.getProperty("enemy.width"), ConfigurationLoader.getProperty("enemy.height"));
                enemyShip.setBounds(0, 0, ConfigurationLoader.getProperty("enemy.width"), ConfigurationLoader.getProperty("enemy.height")); // Set bounds explicitly
                return enemyShip;




            case "EnemyProjectile":
                System.out.println("Creating EnemyProjectile sprite...");
                EnemyProjectileSprite enemyProjectile = new EnemyProjectileSprite( ConfigurationLoader.getProperty("projectile.width"), ConfigurationLoader.getProperty("projectile.height"));
                enemyProjectile.setBounds(0, 0,  ConfigurationLoader.getProperty("projectile.width"),  ConfigurationLoader.getProperty("projectile.height")); // Set bounds explicitly
                return enemyProjectile;
            default:
                System.out.println("Unknown sprite type: " + spriteType);
                throw new IllegalArgumentException("Unknown sprite type: " + spriteType);
        }
    }
}
