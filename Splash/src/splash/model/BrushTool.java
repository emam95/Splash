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
class BrushTool extends Tool {

    Brush brush;
    Layer selected;

    public BrushTool(String brushname) {
        brush = BrushFactory.createBrush(brushname);
        this.id = brushname;
    }

    @Override
    public void primaryKey(int x, int y, Color col) {
        if ((selected = GUIMgr.getWorkSpace().getSelectedLayer()) == null) {
            return;
        }

        if (selected instanceof ObjectLayer) {
            GUIMgr.triedToEditObject();
        } else {
            selected.stroke(brush);            
        }
    }

    @Override
    public void mouseMoved(int x, int y) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void secKey() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
