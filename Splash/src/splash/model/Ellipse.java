package splash.model;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import javafx.scene.paint.Color;

public class Ellipse extends Object2D {

    private int attribute;

    @Override
    public void primaryKey(Point start, Color col) {
        super.primaryKey(start, col);
    }

    @Override
    public void mouseMoved(Point newpos) {
        Point p = newpos.subtract(dstart);
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
        int fxcol = getFillcolor();
        int cancol = getCanvascolor();
        if (getIsFilled()) {
            if (nullifyfill) {
                gpx.setColor(null);
            } else {
                gpx.setColor(new java.awt.Color(fxcol, true));
            }
            gpx.fillOval(x, y, width, height);
            gpx.setColor(new java.awt.Color(cancol, true));
            gpx.drawOval(x, y, width, height);
        } else {
            gpx.drawOval(x, y, width, height);
        }
    }
}
