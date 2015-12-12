/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package splash.model;

;

import java.awt.Rectangle;
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

    boolean inuse = false;
    Point dist;
    Point prevpos;
    Layer selected;

    @Override
    public void primaryKey(int x, int y, Color col) {
        if (inuse) {
            stop();
            return;
        }
        if ((selected = GUIMgr.getWorkSpace().getSelectedLayer()) != null) {
            inuse = true;
            prevpos = selected.getPos();
            dist = (new Point(x, y)).subtract(selected.getX(), selected.getY());
        }
    }

    @Override
    public void mouseMoved(int x, int y) {
        if (inuse) {
            selected.transformTo(new Point(x, y).subtract(dist));
            GUIMgr.getWorkSpace().getSelection().setPos(selected.getPos());
        }
    }

    @Override
    public void secKey() {
        stop();
    }

    void stop() {
        inuse = false;
        Point p = selected.getPos();
        Layer target = selected;
        Point prevp = new Point(prevpos.getX(), prevpos.getY());
        WorkSpace ws = GUIMgr.getWorkSpace();
        CommandCenter.StoreCommand(new Command() {
            @Override
            public void execute() {
                Rectangle orect = target.getRect();
                target.setX(p.getX());
                target.setY(p.getY());
                Rectangle nrect = target.getRect();
                ws.redrawRegion(orect, nrect);
                ws.setSelection(new Selection(target.getRect()));
            }

            @Override
            public void unexecute() {
                Rectangle orect = target.getRect();
                target.setX(prevp.getX());
                target.setY(prevp.getY());
                Rectangle nrect = target.getRect();
                ws.redrawRegion(orect, nrect);
                ws.setSelection(new Selection(target.getRect()));
            }
        });
    }

    @Override
    public void select() {
        super.select();
        if ((selected = GUIMgr.getWorkSpace().getSelectedLayer()) != null) {
            GUIMgr.getWorkSpace().setSelection(new Selection(selected.getRect()));
            GUIMgr.getWorkSpace().setOnSelectedLayerChanged(new LayerChangedEventHandler() {
                @Override
                public void selectedLayerChanged(Layer selectedlayer) {
                    if (Tool.lastselected == PointerTool.this) {
                        if(selectedlayer != null)
                        GUIMgr.getWorkSpace().setSelection(new Selection((selected = selectedlayer).getRect()));
                        else
                            GUIMgr.getWorkSpace().getSelection().clear();
                    }
                }
            });
        }
    }
}
