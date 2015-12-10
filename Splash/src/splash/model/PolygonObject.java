/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package splash.model;

import javafx.scene.paint.Color;

/**
 *
 * @author Hesham
 */
public class PolygonObject extends Polygon {

    @Override
    public void primaryKey(Point p, Color col) {
        if (!wasDrawn() && !isdrawing()) {
            dstart = cpos = p;
            setColor(col);
            addPoint(new Point(0, 0));
            addPoint(new Point(0, 0));
            isdrawing = true;
        } else if (isdrawing) {
            addPoint(p.subtract(dstart));
        }
    }

    @Override
    public void mouseMoved(Point newpos) {
        Point p = newpos.subtract(dstart);
        xs[xs.length - 1] = p.getX();
        ys[ys.length - 1] = p.getY();
    }

    @Override
    public void secKey() {
        isdrawing = false;
        wasdrawn = true;
    }

    private void addPoint(Point p) {
        // This is in order to avoid casting an arraylist to an array on drawing
        int xshift = 0, yshift = 0;
        if (p.getX() < 0) {
            xshift = p.getX() * -1;
        }
        if (p.getY() < 0) {
            yshift = p.getY() * -1;
        }
        int[] tx = new int[xs.length + 1];
        for (int i = 0; i < xs.length; i++) {
            tx[i] = xs[i] + xshift;
        }
        int[] ty = new int[ys.length + 1];
        for (int i = 0; i < ys.length; i++) {
            ty[i] = ys[i] + yshift;
        }
        System.arraycopy(ys, 0, ty, 0, ys.length);
        tx[xs.length] = p.getX() + xshift;
        ty[ys.length] = p.getY() + yshift;
        xs = tx;
        ys = ty;
    }

    @Override
    public boolean layerShouldAdjust() {
        return false;
    }
}
