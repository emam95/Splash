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
    Layer selected;
    boolean isselected = false;

    @Override
    public void select() {
        super.select();
        isselected = true;
        selected = GUIMgr.getWorkSpace().getSelectedLayer();
        GUIMgr.getWorkSpace().clearSelection();
        if (selected != null) {
            selected.applyAdjustedPos();
        }
        imposeSelectionModel();
        GUIMgr.getWorkSpace().setOnSelectedLayerChanged(new LayerChangedEventHandler() {
            @Override
            public void selectedLayerChanged(Layer selectedlayer) {
                if (!isselected) {
                    return;
                }
                selected = selectedlayer;
                GUIMgr.getWorkSpace().clearSelection();
                if (selected != null) {
                    selected.applyAdjustedPos();
                }
                imposeSelectionModel();
            }
        });
    }

    @Override
    public void unselect() {
        isselected = false;
        Rectangle orect = GUIMgr.getWorkSpace().getSelection().getRect();
        GUIMgr.getWorkSpace().getSelection().clear();
        GUIMgr.getWorkSpace().redrawRegion(orect, null);
    }

    @Override
    public void primaryKey(int x, int y, Color col) {
        GUIMgr.getWorkSpace().getSelection().primaryKey(x, y);
    }

    @Override
    public void mouseMoved(int x, int y) {
        GUIMgr.getWorkSpace().getSelection().mouseMoved(x, y);
    }

    @Override
    public void secKey() {
        GUIMgr.getWorkSpace().getSelection().secKey();
    }

    private void imposeSelectionModel() {
        Rectangle orect = GUIMgr.getWorkSpace().getSelection().getRect();
        GUIMgr.getWorkSpace().getSelection().clear();
        if (selected != null) {
            GUIMgr.getWorkSpace().setSelection(new ResizeRectangle(selected));
        } else {
            GUIMgr.getWorkSpace().redrawRegion(orect, null);
        }
    }

}
