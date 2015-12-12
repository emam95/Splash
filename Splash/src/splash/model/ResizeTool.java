/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package splash.model;

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
        GUIMgr.getWorkSpace().clearSelection();
    }

    @Override
    public void primaryKey(int x, int y, Color col) {
        ResizeRectangle.getInstance().primaryKey(x, y);
    }

    @Override
    public void mouseMoved(int x, int y) {
        ResizeRectangle.getInstance().mouseMoved(x, y);
    }

    @Override
    public void secKey() {
        ResizeRectangle.getInstance().secKey();
    }

    private void imposeSelectionModel() {
        GUIMgr.getWorkSpace().clearSelection();
        if (selected != null) {
            GUIMgr.getWorkSpace().setSelection(new ResizeRectangle(selected));
        }
    }

}
