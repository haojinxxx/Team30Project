package TestGrupp.Model;

import javax.vecmath.Point2d;
import java.util.Timer;
import java.util.TimerTask;

public abstract class PowerUp extends GameObject {
    //Constructor for powerup
    public PowerUp(Point2d position, GameEventListener listener) {
        super(position, 0, 1, 1, listener);

    }
    //Abstract method for powerup functionality
    public abstract void PowerUp(PlayerShip playerShip);
    //Activates subclass own powerUp implemen
    public void activatePowerUp(PlayerShip playerShip) {
        PowerUp(playerShip);
        }
    public void StorePowerUp(PlayerShip playerShip, PowerUp powerUp) {
        if (powerUp instanceof shieldPowerUp) {
            playerShip.ShieldPowerUpStatus(true);
        }
        else if (powerUp instanceof healthPowerUp) {
            playerShip.HealthPowerUpStatus(true);
        }
    }
    //Timer for powerup
    public void startPowerUpTimer(Runnable task, long duration) {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                task.run();
            }
        }, duration);
    }


}
