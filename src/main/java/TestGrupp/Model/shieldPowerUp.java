package TestGrupp.Model;

public class shieldPowerUp extends PowerUp {

    public shieldPowerUp(double xPos, double yPos) {
        super(xPos, yPos);
    }

    public void powerUp(PlayerShip playerShip) {
        playerShip.hasShield = true;
        startPowerUpTimer(() -> playerShip.hasShield = false, 10000);
    }
}
