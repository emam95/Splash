/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package splash.model;

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javafx.scene.paint.Color;

/**
 *
 * @author Hesham
 */
public class Line extends Object2D {
    
    Point p;
    float thickness = 1;

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
        BufferedImage output = new BufferedImage(Math.max(1, p.getX()), Math.max(1, p.getY()), BufferedImage.TYPE_INT_ARGB);
        Graphics2D gpx = (Graphics2D)output.getGraphics();
        java.awt.Color col = new java.awt.Color(Helper.getARGB(getColor()), true);
        gpx.setColor(col);        
        gpx.setStroke(new BasicStroke(10));
        gpx.drawLine(0, 0, p.getX()-1, p.getY()-1);        
        return output;
    }
}
