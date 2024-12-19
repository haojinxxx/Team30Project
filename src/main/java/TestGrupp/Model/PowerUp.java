package TestGrupp.Model;

import javax.vecmath.Point2d;
import java.util.Timer;
import java.util.TimerTask;

public abstract class PowerUp extends GameObject {
    //Constructor for powerup
    public PowerUp(Point2d position, GameEventListener listener) {
        super(position, 0, listener);

    }
    //Abstract method for powerup functionality
    public abstract void PowerUp(PlayerShip playerShip);
    //Activates subclass own powerUp implemen
    public void activatePowerUp(PlayerShip playerShip) {
        PowerUp(playerShip);
        playerShip.removePowerUp(this);
        }

    //Timer for power-ups
    public void startPowerUpTimer(Runnable task, long duration) {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                task.run();
            }
        }, duration);
    }

    public void onCollision(GameObject other) {
        if (other instanceof PlayerShip) {

            this.setActive(false);
            // Not sure if you want to handle this here or in the playerShip class's onCollision method but the setActive(false) should be here
        }
    }


}
