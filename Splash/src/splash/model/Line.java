/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package splash.model;

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import javafx.scene.paint.Color;

/**
 *
 * @author Hesham
 */
public class Line extends Object2D {

    Point p;
    float thickness = 2;

    @Override
    public void startDrawing(Point start, Color col) {
        dstart = cpos = start;
        p = new Point(0, 0);
        setColor(col);
    }

    @Override
    public void mouseMoved(Point newpos) {
        if (dstart != null) {
            cpos = newpos;
            p = cpos.subtract(dstart);
        }
    }

    @Override
    public BufferedImage getBitmap() {
        int ox = 0, oy = 0;
        if (p.getX() < 0) {
            ox = p.getX() * -1;
            p.setX(0);
        }
        if (p.getY() < 0) {
            oy = p.getY() * -1;
            p.setY(0);
        }
        BufferedImage output = new BufferedImage(Math.max(1, Math.max(ox, p.getX())), Math.max(1, Math.max(oy, p.getY())), BufferedImage.TYPE_INT_ARGB);
        Graphics2D gpx = (Graphics2D) output.getGraphics();
        java.awt.Color col = new java.awt.Color(Helper.getARGB(getColor()), true);
        if (thickness > 1) {
            gpx.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        }
        gpx.setColor(col);
        gpx.setStroke(new BasicStroke(thickness));
        gpx.drawLine(ox, oy, p.getX(), p.getY());
        width = output.getWidth();
        height = output.getHeight();
        return output;
    }
}
