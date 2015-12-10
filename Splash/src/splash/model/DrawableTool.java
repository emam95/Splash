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


public class DrawableTool extends Tool {
    
    ObjectLayer activelayer = null;
    private final String drawableName;    
    
    public DrawableTool(String drawableName) {
        this.drawableName = drawableName;
        this.id = drawableName;
    }
    
    public String getDrawableName() {
        return drawableName;
    }
    
    @Override
    public void primaryKey(int x, int y, Color col) {
        if (activelayer != null && activelayer.isDrawing()) {
            activelayer.primaryKey(x, y, col);
            return;
        }
        Drawable dr = DrawableFactory.createDrawable(drawableName);
        if (dr instanceof Object2D) {
            activelayer = new ObjectLayer((Object2D) dr);
            GUIMgr.newLayer(activelayer);
            activelayer.setX(x);
            activelayer.setY(y);
            activelayer.primaryKey(x, y, col);
        }
        // TODO: implement free hand tools
    }
    
    @Override
    public void mouseMoved(int x, int y) {        
        activelayer.mouseMoved(x, y);
    }
    
    @Override
    public void secKey() {
        activelayer.finishDrawing();
    }
    
}
