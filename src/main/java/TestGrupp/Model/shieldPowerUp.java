package TestGrupp.Model;

import javax.vecmath.Point2d;

public class shieldPowerUp extends PowerUp {

    public shieldPowerUp(Point2d position, GameEventListener listener) {
        super(position, listener);
    }

    public void PowerUp(PlayerShip playerShip) {
                playerShip.flipShieldStatus();
                startPowerUpTimer(playerShip::flipShieldStatus, 10000);
        }
}

