package TestGrupp.Model;
import java.awt.geom.Rectangle2D;
public class TransformComponent {
    private float x;
    private float y;
    private float rotation;
    private float scaleX;
    private float scaleY;



    public TransformComponent(float x, float y, float rotation, float scaleX, float scaleY) {
        this.x = x;
        this.y = y;
        this.rotation = rotation;
        this.scaleX = scaleX;
        this.scaleY = scaleY;


    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getRotation() {
        return rotation;
    }

    public void setRotation(float rotation) {
        this.rotation = rotation;
    }

    public float getScaleX() {
        return scaleX;
    }

    public void setScaleX(float scaleX) {
        this.scaleX = scaleX;
    }

    public float getScaleY() {
        return scaleY;
    }

    public void setScaleY(float scaleY) {
        this.scaleY = scaleY;
    }

    public Rectangle2D.Float getBoundingBox() {
        return new Rectangle2D.Float(x, y, scaleX, scaleY);
    }

}