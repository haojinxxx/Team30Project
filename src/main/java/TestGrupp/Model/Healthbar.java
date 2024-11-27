package TestGrupp.Model;

public class Healthbar {

    public Healthbar(int health) {
        healthAmmount = health;
    }
    public int healthAmmount;

    public void addHealth(int healthAdded) {
        healthAmmount += healthAdded;
    }
    public void removeHealth(int healthRemoved) {
        healthAmmount -= healthRemoved;
    }
}
