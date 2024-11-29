package TestGrupp.Model;

import java.util.Timer;
import java.util.TimerTask;

public class PowerUp extends GameObject {


    private double x, y; //Position
    private String type; //Type of powerup


    public PowerUp(double x, double y, String type) {
        super(x,y,0,1,1);

        this.x = x;
        this.y = y;
        this.type = type;
    }

    public void activatePowerUp(PlayerShip playerShip) {
        switch (type) {
            case "shield":
                shieldPowerUp(playerShip);
                break;
            case "health":
                healthPowerUp(playerShip);
                break;
        }
    }

    //Shield powerup
    public void shieldPowerUp(PlayerShip playerShip) {
        playerShip.hasShield = true;
        startPowerUpTimer(() -> playerShip.hasShield = false, 10000);
    }
    //Health powerup
    public void healthPowerUp(PlayerShip playerShip) {
        playerShip.addHealth(20);
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
