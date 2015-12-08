package splash.model;

import java.awt.Point;
import java.awt.image.BufferedImage;

public class Ellipse extends Object2D {

    private Point center;
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

    @Override
    public BufferedImage getBitmap() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setWidth(int par0) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setHeight(int val) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
