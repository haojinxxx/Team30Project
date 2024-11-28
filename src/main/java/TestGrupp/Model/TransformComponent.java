package TestGrupp.Model;
import java.awt.geom.Rectangle2D;
public class TransformComponent {
    private double x;
    private double y;
    private double rotation;
    private double scaleX;
    private double scaleY;



    public TransformComponent(double x, double y, double rotation, double scaleX, double scaleY) {
        this.x = x;
        this.y = y;
        this.rotation = rotation;
        this.scaleX = scaleX;
        this.scaleY = scaleY;


    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getRotation() {
        return rotation;
    }

    public void setRotation(double rotation) {
        this.rotation = rotation;
    }

    public double getScaleX() {
        return scaleX;
    }

    public void setScaleX(double scaleX) {
        this.scaleX = scaleX;
    }

    public double getScaleY() {
        return scaleY;
    }

    public void setScaleY(double scaleY) {
        this.scaleY = scaleY;
    }

    public Rectangle2D.Float getBoundingBox() {
        return new Rectangle2D.Float((float) x, (float) y, (float) scaleX, (float) scaleY);
    }

}