package TestGrupp.Model;

import java.util.Timer;
import java.util.TimerTask;

public abstract class PowerUp extends GameObject {
    //Constructor for powerup
    public PowerUp(double xPos, double yPos) {
        super(xPos,yPos,0,1,1);

    }
    //Abstract method for powerup functionality
    public abstract void powerUp(PlayerShip playerShip);
    //Activates subclass own powerUp implemen
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
