/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package splash.model;

import java.util.ArrayList;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import splash.controller.GUIMgr;

/**
 *
 * @author MEmam
 */
public class MultipleSelectionTool extends Tool{
    
    private static ArrayList<Layer> sl = new ArrayList<>();
    
    public MultipleSelectionTool()
    {
        this.id = "Multiple";
    }


    @Override
    public void primaryKey(int x, int y, Color col) {
        if (GUIMgr.isKeyPressed(KeyCode.CONTROL)) {
            GUIMgr.getWorkSpace().selectLayerAt(x, y);
        }
    }

    @Override
    public void mouseMoved(int x, int y) {
    }

    @Override
    public void secKey() {
    }
    
    @Override 
    public void select() {
        super.select();
        Layer selected;
        if ((selected = GUIMgr.getWorkSpace().getSelectedLayer()) != null) {
            sl.add(selected);
        }
        if ((selected = GUIMgr.getWorkSpace().getSelectedLayer()) != null) {
            GUIMgr.getWorkSpace().setSelection(new Selection(selected.getRect()));
            GUIMgr.getWorkSpace().setOnSelectedLayerChanged(new LayerChangedEventHandler() {
                @Override
                public void selectedLayerChanged(Layer selectedlayer) {
                    if (Tool.lastselected == MultipleSelectionTool.this) {
                        if (selectedlayer != null) {
                            GUIMgr.getWorkSpace().addSelection(new Selection((selectedlayer).getRect()));
                            sl.add(selectedlayer);
                        } else {
                            GUIMgr.getWorkSpace().clearSelection();
                            sl.clear();
                        }
                    }
                }
            });
        }
    }
    
    public static void ClearSelection()
    {
        sl.clear();
    }
    
    public static ArrayList<Layer> getSelectedLayers()
    {
        return sl;
    }
    
}
