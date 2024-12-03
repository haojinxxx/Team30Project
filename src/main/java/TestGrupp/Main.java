package TestGrupp;

import TestGrupp.Controller.Controller;
import TestGrupp.Model.GameEventListener;
import TestGrupp.Model.GameModel;
import TestGrupp.View.Panel;

public class Main {

    public static void main(String[] args) {
        Panel view = new Panel("Cosmic Shooter");
        GameModel gm = new GameModel();
        Controller controller = new Controller(gm, view);
    }
}