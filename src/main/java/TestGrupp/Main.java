// src/main/java/TestGrupp/Main.java
package TestGrupp;

import TestGrupp.Controller.Controller;
import TestGrupp.Model.GameModel;
import TestGrupp.View.Panel;

public class Main {
    public static void main(String[] args) {
        Panel view = new Panel("Cosmic Shooter");
        GameModel gm = new GameModel();
        gm.addObserver(view); // Register the view as an observer
        Controller controller = new Controller(gm, view);
        controller.startGame();
    }
}