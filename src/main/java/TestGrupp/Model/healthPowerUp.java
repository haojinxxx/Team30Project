package TestGrupp.Model;

public class healthPowerUp extends PowerUp {

    public healthPowerUp(double xPos, double yPos) {
        //super(xP);
    }

    public void powerUp(PlayerShip playerShip) {
        playerShip.addHealth(20);
    }
}
