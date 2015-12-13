/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package splash.model;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import javafx.scene.paint.Color;

/**
 *
 * @author Hesham
 */
public class RoundedRect extends Object2D {

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
            gpx.fillRoundRect(x, y, width, height, (int) (width * 0.2f), (int) (height * 0.2f));
            gpx.setColor(new java.awt.Color(cancol, true));
            gpx.drawOval(x, y, width, height);
        } else {
            gpx.drawOval(x, y, width, height);
        }
    }
}
