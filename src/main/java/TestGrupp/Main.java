// src/main/java/TestGrupp/Main.java
package TestGrupp;

import TestGrupp.Controller.Controller;
import TestGrupp.Controller.SoundManager;
import TestGrupp.Model.GameModel;
import TestGrupp.View.View;

public class Main {
    public static void main(String[] args) {
        View view = new View("Cosmic Shooter");
        GameModel gm = new GameModel();
        gm.addObserver(view); // Register the view as an observer
        gm.addScoreObserver(view); // Register the view as a score observer
        Controller controller = new Controller(gm, view);




        controller.startGame();
    }
}