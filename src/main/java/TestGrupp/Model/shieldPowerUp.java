package TestGrupp.Model;

public class shieldPowerUp extends PowerUp {

    public shieldPowerUp(double x, double y) {
        super(x, y);
    }

    public void powerUp(PlayerShip playerShip) {
        playerShip.hasShield = true;
        startPowerUpTimer(() -> playerShip.hasShield = false, 10000);
    }
}
