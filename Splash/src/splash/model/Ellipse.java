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
    public void drawToBitmap(BufferedImage target, int x, int y) {
        Graphics2D gpx = target.createGraphics();
        gpx.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        Color fxcol = getFillColor();
        Color cancol = getCanvascolor();
        if (getIsFilled()) {
            if (fxcol == null) {
                gpx.setColor(null);
            } else {
                gpx.setColor(new java.awt.Color(Helper.getARGB(fxcol), true));
            }
            gpx.fillOval(x, y, width, height);
            if (cancol != null) {
                gpx.setColor(new java.awt.Color(Helper.getARGB(cancol), true));
                gpx.drawOval(x, y, width, height);
            }
        } else {
            gpx.drawOval(x, y, width, height);
        }
    }

}
