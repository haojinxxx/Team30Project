package TestGrupp.Model;

import java.util.Timer;
import java.util.TimerTask;

public abstract class PowerUp extends GameObject {

    public PowerUp(double x, double y) {
        super(x,y,0,1,1);

    }
    public abstract void powerUp(PlayerShip playerShip);

    public void activatePowerUp(PlayerShip playerShip) {
        powerUp(playerShip);
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
