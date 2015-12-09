/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package splash.model;

;

import javafx.scene.paint.Color;
import splash.controller.GUIMgr;

/**
 *
 * @author Hesham
 */


public class PointerTool extends Tool {

    public PointerTool() {
        id = "Pointer";
    }

    @Override
    public void initFunction(Point startpoint) {
        // TODO: Handle move
    }
    boolean inuse = false;
    Point dist;

    @Override
    public void startDrawing(int x, int y, Color col) {
        Layer selected;
        if ((selected = GUIMgr.getWorkSpace().getSelectedLayer()) != null) {
            inuse = true;
            dist = (new Point(x, y)).subtract(selected.getX(), selected.getY());
        }
    }

    @Override
    public void mouseMoved(int x, int y) {
        if (inuse) {
            GUIMgr.getWorkSpace().getSelectedLayer().transformTo(new Point(x, y).subtract(dist));
        }
    }

    @Override
    public void finishDrawing() {
        inuse = false;
    }

}
