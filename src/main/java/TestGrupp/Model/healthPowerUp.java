package TestGrupp.Model;

import javax.vecmath.Point2d;

public class healthPowerUp extends PowerUp {

    public healthPowerUp(Point2d position, GameEventListener listener) {
        super(position, listener);
        setType("Health");
    }

    public void PowerUp(PlayerShip playerShip) {
            playerShip.addHealth(20);
        }
}

