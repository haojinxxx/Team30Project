package TestGrupp.Model;

import javax.vecmath.Point2d;

public class healthPowerUp extends PowerUp {

    public healthPowerUp(Point2d position, GameEventListener listener) {
        super(position, listener);
    }

    public void PowerUp(PlayerShip playerShip) {
        if(playerShip.getHealthPowerUpStatus()){
            playerShip.addHealth(20);
            playerShip.HealthPowerUpStatus(false);
        }

    }
}
