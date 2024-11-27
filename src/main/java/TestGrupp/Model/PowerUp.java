package TestGrupp.Model;

import java.util.Timer;
import java.util.TimerTask;

public class PowerUp extends GameObject {


    private double x, y; //Position
    private String type; //Type of powerup


    public PowerUp(double x, double y, String type) {
        this.x = x;
        this.y = y;
        this.type = type;
    }

    public void activatePowerUp(Ship ship) {
        switch (type) {
            case "shield":
                shieldPowerUp(ship);
                break;
            case "health":
                healthPowerUp(ship);
                break;
        }
    }

    //Shield powerup
    public void shieldPowerUp(Ship ship) {
        ship.hasShield = true;
        startPowerUpTimer(() -> ship.hasShield = false, 10000);
    }
    //Health powerup
    public void healthPowerUp(Ship ship) {
        ship.addHealth(20);
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
