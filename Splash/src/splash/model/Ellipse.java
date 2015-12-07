package splash.model;

import java.awt.Point;

public class Ellipse extends Object2D {

    private Point center;
    private float width;
    private float height;
    private int attribute;

    public Point getCenter() {
        return this.center;
    }

    /**
     *
     * @param center
     */
    public void setCenter(Point center) {
        this.center = center;
    }

    public float getWidth() {
        return this.width;
    }

    /**
     *
     * @param width
     */
    public void setWidth(float width) {
        this.width = width;
    }

    public float getHeight() {
        return this.height;
    }

    /**
     *
     * @param height
     */
    public void setHeight(float height) {
        this.height = height;
    }

    public void getAttribute() {
        throw new UnsupportedOperationException();
    }

    /**
     *
     * @param attribute
     */
    public void setAttribute(int attribute) {
        this.attribute = attribute;
    }
}
