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
                return new PlayerShipSprite(configLoader.getProperty("player.width"), configLoader.getProperty("player.height"));
            case "PlayerProjectile":
                System.out.println("Creating PlayerProjectile sprite...");
                return new PlayerProjectileSprite(configLoader.getProperty("projectile.width"), configLoader.getProperty("projectile.height"));
            case "PowerUp":
                System.out.println("Creating PowerUp sprite...");
                return new PowerUpSprite(configLoader.getProperty("powerup.width"), configLoader.getProperty("powerup.height"));
            case "Asteroid":
                System.out.println("Creating Asteroid sprite...");
                return new AsteroidSprite(configLoader.getProperty("asteroid.width"), configLoader.getProperty("asteroid.height"));
            default:
                System.out.println("Unknown sprite type: " + spriteType);
                throw new IllegalArgumentException("Unknown sprite type: " + spriteType);
        }
    }
}
