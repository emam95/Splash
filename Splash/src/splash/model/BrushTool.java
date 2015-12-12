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
    boolean inuse = false;

    public BrushTool(String brushname) {
        brush = BrushFactory.createBrush(brushname);
        this.id = brushname;
    }

    public BrushTool() {
    }

    @Override
    public void primaryKey(int x, int y, Color col) {
        if ((selected = GUIMgr.getWorkSpace().getSelectedLayer()) == null) {
            return;
        }

        if (selected instanceof ObjectLayer) {
            GUIMgr.triedToEditObject();
        } else if (!inuse) {
            inuse = true;
            selected.stroke(brush, x, y);
        }
    }

    @Override
    public void mouseMoved(int x, int y) {
        if (inuse) {
            selected.stroke(brush, x, y);
        }
    }

    @Override
    public void secKey() {
        inuse = false;
    }

}
