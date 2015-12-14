/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package splash.model;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import splash.controller.GUIMgr;

/**
 *
 * @author MEmam
 */
public class LayerGroup extends Layer {

    List<Layer> layers = new ArrayList<Layer>();

    public void addLayer(Layer l) {
        layers.add(l);
    }

    @Override
    public void transform(Point diff) {
        for (Layer l : layers) {
            l.transform(diff);
        }
    }

    @Override
    public void resizeX(int val) {
        float cx = getCenter().getX();
        float rf = (float) val / getWidth();
        for (Layer l : layers) {
            float nx = l.getX() - cx;
            nx *= rf;
            l.setX((int) (nx + val / 2f));
            l.resizeX(val);
        }
        //width = val;            
    }

    @Override
    public void resizeY(int val) {
        float cy = getCenter().getY();
        float rf = (float) val / getHeight();
        for (Layer l : layers) {
            float ny = l.getY() - cy;
            ny *= rf;
            l.setY((int) (ny + val / 2f));
            l.resizeY(val);
        }
        //height = val;
    }

    @Override
    public void addWidthRel(int dif, int i) {
        for (Layer l : layers) {
            l.addWidthRel(dif, i);
        }
    }

    @Override
    public void addHeightRel(int dif, int i) {
        for (Layer l : layers) {
            l.addHeightRel(dif, i);
        }
    }

    @Override
    public HashMap<String, Property> getEditableList() {
        return null;
    }

    @Override
    public int getWidth() {
        int x = getX();
        int w = 0;
        for (Layer l : layers) {
            w = Math.max(w, l.getX() + l.getWidth());
        }
        return w;
    }

    @Override
    int getHeight() {
        int y = getY();
        int h = 0;
        for (Layer l : layers) {
            h = Math.max(h, l.getY() + l.getHeight());
        }
        return h;
    }

    @Override
    public void adjustX(int i) {
        for (Layer l : layers) {
            l.adjustX(i);
        }
    }

    public void adjustY(int i) {
        for (Layer l : layers) {
            l.adjustY(i);
        }
    }

    public int getX() {
        int gx = layers.get(0).getX();
        for (Layer l : layers) {
            gx = Math.min(gx, l.getX());
        }
        return gx;
    }

    public int getY() {
        int gy = layers.get(0).getY();
        for (Layer l : layers) {
            gy = Math.min(gy, l.getY());
        }
        return gy;
    }

    public void applyAdjustedPos() {
        for (Layer l : layers) {
            l.applyAdjustedPos();
        }
    }

    public void setX(int val) {
        int x = getX();
        int dif = val - x;
        for (Layer l : layers) {
            l.transform(dif, 0);
        }
    }

    public void setY(int val) {
        int Y = getX();
        int dif = val - Y;
        for (Layer l : layers) {
            l.transform(0, dif);
        }
    }
}
