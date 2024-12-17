package TestGrupp.View;

public class SpriteFactory {
    private ConfigurationLoader configLoader;

    public SpriteFactory(ConfigurationLoader configLoader) {
        this.configLoader = configLoader;
    }

    public Sprite createSprite(String spriteType) {
        System.out.println("createSprite called with spriteType: " + spriteType);
        switch (spriteType) {
            case "PlayerShip":
                System.out.println("Creating PlayerShip sprite...");
                PlayerShipSprite playerShipSprite = new PlayerShipSprite(configLoader.getProperty("player.width"), configLoader.getProperty("player.height"));
                playerShipSprite.setBounds(0, 0, configLoader.getProperty("player.width"), configLoader.getProperty("player.height")); // Set bounds explicitly
                return playerShipSprite;
            case "PlayerProjectile":
                System.out.println("Creating PlayerProjectile sprite...");
                PlayerProjectileSprite playerProjectileSprite = new PlayerProjectileSprite(configLoader.getProperty("projectile.width"), configLoader.getProperty("projectile.height"));
                playerProjectileSprite.setBounds(0, 0, configLoader.getProperty("projectile.width"), configLoader.getProperty("projectile.height")); // Set bounds explicitly
                return playerProjectileSprite;
            case "PowerUp":
                System.out.println("Creating PowerUp sprite...");
                PowerUpSprite powerUpSprite = new PowerUpSprite(configLoader.getProperty("powerup.width"), configLoader.getProperty("powerup.height"));
                powerUpSprite.setBounds(0, 0,configLoader.getProperty("powerup.width"), configLoader.getProperty("powerup.height")); // Set bounds explicitly
                return powerUpSprite;
            case "Asteroid":
                System.out.println("Creating Asteroid sprite...");
                AsteroidSprite asteroidSprite = new AsteroidSprite(configLoader.getProperty("asteroid.width"), configLoader.getProperty("asteroid.height"));
                asteroidSprite.setBounds(0, 0, configLoader.getProperty("asteroid.width"), configLoader.getProperty("asteroid.height")); // Set bounds explicitly
                return asteroidSprite;
            default:
                System.out.println("Unknown sprite type: " + spriteType);
                throw new IllegalArgumentException("Unknown sprite type: " + spriteType);
        }
    }
}
