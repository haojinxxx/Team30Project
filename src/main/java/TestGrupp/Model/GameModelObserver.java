package TestGrupp.Model;

import javax.vecmath.Point2d;

public interface GameModelObserver {
    void onGameModelChanged(Point2d pos);
}
