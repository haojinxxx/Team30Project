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
                PlayerShipSprite playerShipSprite = new PlayerShipSprite(ConfigurationLoader.getProperty("PlayerShip.width"), ConfigurationLoader.getProperty("PlayerShip.height"));
                playerShipSprite.setBounds(0, 0, ConfigurationLoader.getProperty("PlayerShip.width"), ConfigurationLoader.getProperty("PlayerShip.height")); // Set bounds explicitly
                return playerShipSprite;
            case "PlayerProjectile":
                System.out.println("Creating PlayerProjectile sprite...");
                PlayerProjectileSprite playerProjectileSprite = new PlayerProjectileSprite(ConfigurationLoader.getProperty("Projectile.width"), ConfigurationLoader.getProperty("Projectile.height"));
                playerProjectileSprite.setBounds(0, 0, ConfigurationLoader.getProperty("Projectile.width"), ConfigurationLoader.getProperty("Projectile.height")); // Set bounds explicitly
                return playerProjectileSprite;
            case "PowerUp":
                System.out.println("Creating PowerUp sprite...");
                PowerUpSprite powerUpSprite = new PowerUpSprite(ConfigurationLoader.getProperty("Powerup.width"), ConfigurationLoader.getProperty("Powerup.height"));
                powerUpSprite.setBounds(0, 0,ConfigurationLoader.getProperty("Powerup.width"), ConfigurationLoader.getProperty("Powerup.height")); // Set bounds explicitly
                return powerUpSprite;
            case "Asteroid":
                System.out.println("Creating Asteroid sprite...");
                AsteroidSprite asteroidSprite = new AsteroidSprite(ConfigurationLoader.getProperty("Asteroid.width"), ConfigurationLoader.getProperty("Asteroid.height"));
                asteroidSprite.setBounds(0, 0, ConfigurationLoader.getProperty("Asteroid.width"), ConfigurationLoader.getProperty("Asteroid.height")); // Set bounds explicitly
                return asteroidSprite;

            case "EnemyShip":
                System.out.println("Creating EnemyShip sprite...");
                EnemyShipSprite enemyShip = new EnemyShipSprite(ConfigurationLoader.getProperty("EnemyShip.width"), ConfigurationLoader.getProperty("EnemyShip.height"));
                enemyShip.setBounds(0, 0, ConfigurationLoader.getProperty("EnemyShip.width"), ConfigurationLoader.getProperty("EnemyShip.height")); // Set bounds explicitly
                return enemyShip;




            case "EnemyProjectile":
                System.out.println("Creating EnemyProjectile sprite...");
                EnemyProjectileSprite enemyProjectile = new EnemyProjectileSprite( ConfigurationLoader.getProperty("Projectile.width"), ConfigurationLoader.getProperty("Projectile.height"));
                enemyProjectile.setBounds(0, 0,  ConfigurationLoader.getProperty("Projectile.width"),  ConfigurationLoader.getProperty("Projectile.height")); // Set bounds explicitly
                return enemyProjectile;
            default:
                System.out.println("Unknown sprite type: " + spriteType);
                throw new IllegalArgumentException("Unknown sprite type: " + spriteType);
        }
    }
}
