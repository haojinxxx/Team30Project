package TestGrupp.Model;

import javax.vecmath.Point2d;
import javax.vecmath.Vector2d;
import java.util.ArrayList;
import java.util.List;

public class PlayerShip extends GameObject {
    private double maxSpeed;           // Maximum speed of the ship
    private double acceleration;       // Acceleration factor
    private double friction;           // Friction factor (how quickly the ship slows down)
    private HealthComponent health;    // Component to track health
    private int projectileDamage;      // Damage dealt by the ship's projectiles

    private int scaleX;
    private int scaleY;

    private PhysicsComponent physics;  // Component for movement and physics

    private List<Projectile> shipProjectiles; // Projectiles fired by the ship

    private double desiredRotation;    // The target rotation in radians
    private double rotationSpeed;      // Rotation speed in radians per second
    private boolean rotating;          // Whether the ship is actively rotating

    private boolean movingForward;     // Flag for forward movement
    private List<PowerUp> collectedPowerUps;
    private boolean hasShield;         // Whether the ship has an active shield

    public PlayerShip(Point2d position, double rotation, GameEventListener listener) {
        super(position, -Math.PI / 2, listener); // Call to the parent GameObject class

        this.collectedPowerUps = new ArrayList<PowerUp>();
        this.health = new HealthComponent(100); // Start with full health
        this.projectileDamage = 100; // Set the default projectile damage
        this.shipProjectiles = new ArrayList<>();
        this.hasShield = false;

        // Default movement and rotation settings
        this.rotationSpeed = Math.toRadians(200); // Convert degrees/sec to radians/sec
        this.desiredRotation = rotation;
        this.acceleration = 2000; // Increase the acceleration factor
        this.maxSpeed = 250.0;   // Increase the max speed for faster movement
        this.friction = 0.001;    // Decrease friction for slower deceleration
        this.rotating = false;
        this.movingForward = false;

        this.physics = new PhysicsComponent(maxSpeed, 0.95);
    }

    // Handle ship rotation to a specific angle in radians
    public void rotate(double radians) {
        this.desiredRotation = getTransform().getRotation() + radians;
        this.rotating = true;
    }

    // Stop the rotation
    public void stopRotation() {
        this.rotating = false;
    }

    // Set forward movement flag
    public void setMovingForward(boolean movingForward) {
        this.movingForward = movingForward;
    }

    // Set backward movement flag


    public void fire() {
        double projectileSpeed = 400;
        Point2d position = new Point2d(this.getTransform().getPosition());  // Get the current position of the ship
        double rotation = this.getTransform().getRotation();  // Get the rotation (direction the ship is facing)
        int projectileDamage = this.projectileDamage;

        // Define the distance in front of the player where the projectile will spawn
        double offset = 5.0;  // Adjust this distance as needed

        // Calculate the direction the player is facing based on the rotation
        double directionX = Math.cos(rotation);
        double directionY = Math.sin(rotation);

        // Calculate the center of the player ship
        double centerX = position.x + (scaleX / 2.0);
        double centerY = position.y + (scaleY / 2.0);

        // Now, calculate the spawn position in front of the ship (offset from the ship's current position)
        double spawnX = centerX + offset * directionX;
        double spawnY = centerY + offset * directionY;

        // Set the projectile's velocity in the same direction as the ship's facing direction
        Vector2d velocity = new Vector2d(directionX, directionY);
        velocity.scale(projectileSpeed);  // Scale the velocity to the projectile's speed

        // Debugging message
        System.out.printf("I shoot!!! Projectile spawned at: (%f, %f)\n", spawnX, spawnY);

        // If the listener is not null, notify that a projectile has been fired
        if (listener != null) {
            listener.onProjectileFired(new Point2d(spawnX, spawnY), velocity, rotation, projectileSpeed, projectileDamage, true);
        }
    }


    // Retrieve all projectiles fired by the ship
    public List<Projectile> getProjectiles() {
        return shipProjectiles;
    }

    // Take damage, reducing health unless a shield is active
    public void takeDamage(int damage) {
        System.out.printf("Player took %d damage\n", damage);
        this.health.removeHealth(damage);
        if (this.health.getHealth() <= 0) {
            System.out.print("Player destroyed\n");
            this.setActive(false);
            if (listener != null) {
                listener.onPlayerDestroyed();
            }
        }
    }

    // Add health to the ship
    public void addHealth(int healthPoints) {
        this.health.addHealth(healthPoints);
    }

    // Activate the ship's shield
    public void activateShield() {
        this.hasShield = true;
    }

    // Deactivate the ship's shield
    public void deactivateShield() {
        this.hasShield = false;
    }

    // Update the ship's state
    @Override
    public void update(double deltaTime) {
        super.update(deltaTime);

        // Handle rotation logic
        if (rotating) {
            double angleDiff = desiredRotation - getTransform().getRotation();
            angleDiff = Math.atan2(Math.sin(angleDiff), Math.cos(angleDiff)); // Normalize to [-π, π]

            // Smoothly update the rotation to match the desiredRotation
            double rotationChange = rotationSpeed * deltaTime;
            if (Math.abs(angleDiff) > rotationChange) {
                // Rotate towards desired angle with speed limit
                getTransform().setRotation(getTransform().getRotation() + Math.signum(angleDiff) * rotationChange);
            } else {
                // If the angle is close enough, set the rotation to the desired rotation
                getTransform().setRotation(desiredRotation);
                rotating = false;
            }
        }



        // Handle movement logic
        if (movingForward) {
            double moveAngle = getTransform().getRotation(); // Ship's rotation in radians
            double speedMultiplier = movingForward ? 1 : -1; // Determine forward/backward

            // Calculate acceleration vector based on ship's rotation
            double moveX = Math.cos(moveAngle) * acceleration * speedMultiplier;
            double moveY = Math.sin(moveAngle) * acceleration * speedMultiplier;

            // Debugging log for acceleration vector
            System.out.println("Moving Forward: " + movingForward);
            System.out.println("Rotation (radians): " + getTransform().getRotation());
            System.out.println("Acceleration Vector: (" + moveX + ", " + moveY + ")");


            //Implement wrap-around logic
            checkOutofBounds();

            physics.setAcceleration(moveX, moveY); // Set the acceleration to the physics component
        } else {
            physics.setAcceleration(0, 0); // No movement when flags are off
        }

        // Update physics and position
        physics.update(deltaTime, getTransform());
    }


    private void checkOutofBounds() {
        //Singleton arguments doesn't matter because
        //we only need the screen size, which is set in controller when initializing the game
        //ScreenDataSingleton screenData = ScreenDataSingleton.getInstance(0, 0, 0);

        // ^^ I understand it worked in this instance, but when used for asteroids it did not, so moving initialization to main seems appropriate
        ScreenDataSingleton screenData = ScreenDataSingleton.getInstance();
        int maxX = screenData.getWidth();
        int maxY = screenData.getHeight() - screenData.getBottomBarHeight();

        Point2d position = getTransform().getPosition();
        if (position.x < 0) {
            position.x = maxX;
        } else if (position.x > maxX) {
            position.x = 0;
        }
        if (position.y < 0) {
            position.y = maxY;
        } else if (position.y > maxY) {
            position.y = 0;
        }
    }

    @Override
    public void onCollision(GameObject other) {
        if (other instanceof PowerUp) {

            PowerUp powerUp = (PowerUp) other;
            if (powerUp instanceof healthPowerUp) {
                collectPowerUp(powerUp, 0);

            } else if (powerUp instanceof shieldPowerUp) {

                collectPowerUp(powerUp, 1);
            }
        }
        else if (other instanceof Projectile) {
            Projectile projectile = (Projectile) other;
            if (!projectile.isPlayerProjectile()) {
                takeDamage(projectile.getDamage());
            }
        }
        else if (other instanceof Asteroid) {
            takeDamage(10); // we should have a getter for an asteroid damage value like we have for projectiles

        }
    }

    // Get the current health value
    public int getHealth() {
        return health.getHealth();
    }

    //Power up stuff
    public void flipShieldStatus(){
        hasShield = !hasShield;
    }

    public void activateStoredPowerUp(int index) {
        if (index >= 0 && index < collectedPowerUps.size()) {
            PowerUp powerUp = collectedPowerUps.get(index);
            powerUp.activatePowerUp(this);
            collectedPowerUps.remove(index);
        }
    }

    public void collectPowerUp(PowerUp powerUp, int index) {
        collectedPowerUps.add(index, powerUp);
        listener.onPowerUpCollected(powerUp);
    }
          
    public void removePowerUp(PowerUp powerUp) {
        collectedPowerUps.remove(powerUp);
    }


}
