/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package splash.model;

import java.awt.Point;
import splash.controller.GUIMgr;

/**
 *
 * @author Hesham
 */
public class DrawableTool extends Tool {

    ObjectLayer activelayer;
    private final String drawableName;

    public DrawableTool(String drawableName) {
        this.drawableName = drawableName;
    }

    public String getDrawableName() {
        return drawableName;
    }

    @Override
    public void initFunction(Point startpoint) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void startDrawing(int x, int y) {
        Drawable dr = DrawableFactory.createDrawable(drawableName);
        if (dr instanceof Object2D) {
            activelayer = new ObjectLayer(x, y, (Object2D) dr);
            GUIMgr.getWorkSpace().addLayer(activelayer);
            activelayer.startDrawing(x, y);
        }
        // TODO: implement free hand tools
    }

    @Override
    public void mouseOffset(int x, int y) {
        activelayer.mouseOffset(x, y);
    }

    @Override
    public void finishDrawing() {
        activelayer.finishDrawing();
    }

}
