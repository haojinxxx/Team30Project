package TestGrupp.Model;

public class HealthComponent {

    public HealthComponent(int health) {
        healthAmmount = health;
    }
    public int healthAmmount;

    public void addHealth(int healthAdded) {
        healthAmmount += healthAdded;
    }
    public void removeHealth(int healthRemoved) {
        healthAmmount -= healthRemoved;
    }

    public int getHealth() {
        return healthAmmount;
    }
}
