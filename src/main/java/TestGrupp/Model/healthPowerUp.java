package TestGrupp.Model;

public class healthPowerUp extends PowerUp {

    public healthPowerUp(double x, double y) {
        super(x, y);
    }

    public void powerUp(PlayerShip playerShip) {
        playerShip.addHealth(20);
    }
}
