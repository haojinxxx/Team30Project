package TestGrupp;

import TestGrupp.Controller.Controller;
import TestGrupp.Model.GameModel;
import TestGrupp.View.Panel;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        Panel view = new Panel("Cosmic Shooter");
        GameModel gm = new GameModel();
        Controller controller = new Controller(gm,view);
    }
}