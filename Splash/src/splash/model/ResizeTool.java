/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package splash.model;

import java.awt.Rectangle;
import javafx.scene.paint.Color;
import splash.controller.GUIMgr;

/**
 *
 * @author Hesham
 */
class ResizeTool extends Tool {

    public ResizeTool() {
        id = "Resize";
    }
    Selection ts;
    Layer selected;
    boolean isselected = false;

    @Override
    public void select() {
        super.select();
        isselected = true;
        ts = GUIMgr.getWorkSpace().getSelection();
        selected = GUIMgr.getWorkSpace().getSelectedLayer();
        imposeSelectionModel();
        GUIMgr.getWorkSpace().setOnSelectedLayerChanged(new LayerChangedEventHandler() {
            @Override
            public void selectedLayerChanged(Layer selectedlayer) {
                if (!isselected) {
                    return;
                }
                selected = selectedlayer;
                selected.applyAdjustedPos();
                imposeSelectionModel();
            }
        });
    }

    @Override
    public void unselect() {
        isselected = false;
        Rectangle orect = ts.getRect();
        ts.clear();
        GUIMgr.getWorkSpace().redrawRegion(orect, null, true);
    }

    @Override
    public void primaryKey(int x, int y, Color col) {
        ts.primaryKey(x,y);
    }

    @Override
    public void mouseMoved(int x, int y) {
        ts.mouseMoved(x,y);
    }

    @Override
    public void secKey() {
        ts.secKey();
    }

    private void imposeSelectionModel() {
        Rectangle orect = ts.getRect();
        ts.clear();
        if (selected != null) {
            GUIMgr.getWorkSpace().setSelection(ts=new ResizeRectangle(selected));
        } else {
            GUIMgr.getWorkSpace().redrawRegion(orect, null, true);
        }
    }

}
