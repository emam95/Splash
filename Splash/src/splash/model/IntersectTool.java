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
 * @author Hesham
 */
public class IntersectTool extends Tool {

    public IntersectTool() {
        this.id = "Intersect";
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
        RawLayer rl = new RawLayer();
        for (int x = 0; x < GUIMgr.getWorkSpace().getWidth(); x++) {
            for (int y = 0; y < GUIMgr.getWorkSpace().getHeight(); y++) {
                boolean flag = true;
                for (Layer l : sl) {
                    if (!l.contains(x, y) || l.getPixel(x, y).getAlpha() == 0) {
                        flag = false;
                        break;
                    }
                }
                if (flag) {
                    rl.setPixel(x, y, GUIMgr.getSelectedFillColor());
                }
            }
        }
        GUIMgr.newLayer(rl);
    }
    ArrayList<Layer> sl = new ArrayList<>();

    @Override
    public void select() {
        if (Tool.lastselected == IntersectTool.this) {
            secKey();
        }
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
                    if (Tool.lastselected == IntersectTool.this) {
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
}
