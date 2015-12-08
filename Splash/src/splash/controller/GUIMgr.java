package splash.controller;

import java.awt.Color;
import java.awt.Point;
import splash.model.*;

public class GUIMgr {

    public static void clearDrawingArea() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public static WorkSpace getWorkSpace() {
        return workspace;
    }

    private static WorkSpace workspace = null;

    /**
     *
     * @param drawable
     */
    public static Tool getSelectedTool() {
        return splash.model.ResourceManager.getTools().get(0);
    }

    public static Color getPixel(int x, int y) {
        //return gc.getpixelreader().getpixel
        return null;

    }

    public static void setPixel(int x, int y, Color col) {

    }

    public void startDrawing(Drawable drawable, Point startpoint) {

    }

    public void newProject(int width, int height) {
        workspace = new WorkSpace(width, height);
    }

    public void newLayer() {
        throw new UnsupportedOperationException();
    }

    /**
     *
     * @param id
     */
    public void selecLayer(int id) {
        throw new UnsupportedOperationException();
    }

    public void initSave() {
        throw new UnsupportedOperationException();
    }

    public void initLoad() {
        throw new UnsupportedOperationException();
    }

    public void startDrawing(int x, int y, Tool tool) {
        workspace.startDrawing(x, y, tool);
    }

    public void mouseOffset(int x, int y) {
        workspace.mouseOffset(x, y);
    }

    public void finishDrawing() {
        workspace.finishDrawing();
    }
}
