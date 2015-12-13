/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package splash.model;

import java.util.HashMap;
import javafx.scene.paint.Color;

/**
 *
 * @author Hesham
 */
public class PolygonObject extends Polygon {

    @Override
    public void primaryKey(Point p, Color col) {
        if (!wasDrawn() && !isdrawing()) {
            setFillColor(Helper.getARGB(col));
            addPoint(new Point(0, 0));
            addPoint(new Point(0, 0));
            isdrawing = true;
        } else if (isdrawing()) {
            addPoint(p.subtract(parent.getPos()));
        }
    }

    @Override
    public void mouseMoved(Point newpos) {
        Point p = newpos.subtract(parent.getPos());
        replaceLastPoint(p);
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
        float[] tx = new float[xs.length + 1];
        for (int i = 0; i < xs.length; i++) {
            tx[i] = xs[i] + xshift;
        }
        float[] ty = new float[ys.length + 1];
        for (int i = 0; i < ys.length; i++) {
            ty[i] = ys[i] + yshift;
        }
        tx[xs.length] = p.getX() + xshift;
        ty[ys.length] = p.getY() + yshift;
        xs = tx;
        ys = ty;
        parent.transform(-xshift, -yshift);
    }

    private void replaceLastPoint(Point p) {
        int xshift = 0, yshift = 0;
        if (p.getX() < 0) {
            xshift = p.getX() * -1;
        }
        if (p.getY() < 0) {
            yshift = p.getY() * -1;
        }
        if (xshift != 0) {
            for (int i = 0; i < xs.length - 1; i++) {
                xs[i] = xs[i] + xshift;
            }
        }
        if (yshift != 0) {
            for (int i = 0; i < ys.length - 1; i++) {
                ys[i] = ys[i] + yshift;
            }
        }
        xs[xs.length - 1] = p.getX() + xshift;
        ys[ys.length - 1] = p.getY() + yshift;
        parent.transform(-xshift, -yshift);
    }
}
