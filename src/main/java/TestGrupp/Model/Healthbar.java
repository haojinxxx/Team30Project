package TestGrupp.Model;

public class Healthbar implements IHealth {
    private int healthAmmount;

    private void addHealth(int healthAdded) {
        healthAmmount += healthAdded;
    }
    private void removeHealth(int healthRemoved) {
        healthAmmount -= healthRemoved;
    }
}
