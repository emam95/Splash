/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package splash.model;

;

import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import splash.controller.GUIMgr;

/**
 *
 * @author Hesham
 */


public class FillTool extends Tool {
    
    public FillTool() {
        id = "Fill";
    }
    
    @Override
    public void primaryKey(int x, int y, Color col) {
        if (GUIMgr.isKeyPressed(KeyCode.CONTROL)) {
            GUIMgr.getWorkSpace().selectLayerAt(x, y);
            Layer l = GUIMgr.getWorkSpace().getSelectedLayer();
            if (l instanceof ObjectLayer) {
                Object2D obj = ((ObjectLayer) l).getObject();
                if (obj != null) {
                    obj.setFillcolor(GUIMgr.getSelectedFillColor());
                    ((ObjectLayer) l).redraw();
                    GUIMgr.getWorkSpace().redrawRegion(l.getRect(), null);
                }
            }
        }
    }
    
    @Override
    public void mouseMoved(int x, int y) {
    }
    
    @Override
    public void secKey() {
    }
    
}
