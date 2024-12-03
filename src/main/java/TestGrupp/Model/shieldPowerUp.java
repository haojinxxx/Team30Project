package TestGrupp.Model;

import javax.vecmath.Point2d;

public class shieldPowerUp extends PowerUp {

    public shieldPowerUp(Point2d position, GameEventListener listener) {
        super(position, listener);
    }

    public void powerUp(PlayerShip playerShip) {
        playerShip.hasShield = true;
        startPowerUpTimer(() -> playerShip.hasShield = false, 10000);
    }
}
