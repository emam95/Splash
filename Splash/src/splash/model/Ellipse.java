package splash.model;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import javafx.scene.paint.Color;

public class Ellipse extends Object2D {

    private PointF center;
    private int attribute;

    @Override
    public PointF getCenter() {
        return this.center;
    }

    /**
     *
     * @param center
     */
    public void setCenter(PointF center) {
        this.center = center;
    }
    
    @Override
    public void primaryKey(Point start, Color col) {
        setCenter(start.toPointF());
        super.primaryKey(start, col);
    }

    @Override
    public void mouseMoved(Point newpos) {
        Point p = newpos.subtract(dstart);
        int nx = 0, ny = 0;
        if (p.getX() < 0) {
            if (parent != null) {
                parent.adjustX(p.getX());
            }
        }
        if (p.getY() < 0) {
            if (parent != null) {
                parent.adjustY(p.getY());
            }
        }
        super.mouseMoved(newpos);
    }

    @Override
    public HashMap<String, Object> getEditableList() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Error[] updateProperties(HashMap<String, Object> modrec) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void drawToBitmap(BufferedImage target, int x, int y) {
        Graphics2D gpx = target.createGraphics();
        java.awt.Color col = new java.awt.Color(Helper.getARGB(getColor()), true);
        gpx.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        gpx.setColor(col);
        gpx.fillOval(x, y, width, height);
    }

}
