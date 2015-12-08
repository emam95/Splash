package splash.model;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Stack;
import javafx.scene.canvas.GraphicsContext;
import splash.controller.GUIMgr;

public class WorkSpace {

    int width, height;
    private GraphicsContext graphics;
    private ArrayList<Layer> layers = new ArrayList<>();
    private Layer selectedlayer;
    private Stack<State> paststates;
    private Stack<State> futstates;
    private Selection selection;

    public WorkSpace(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public GraphicsContext getGraphics() {
        return this.graphics;
    }

    public ArrayList<Layer> getLayers() {
        return this.layers;
    }

    public void addLayer(Layer layer) {
        if (layers.isEmpty()) {
            selectedlayer = layer;
        }
        layers.add(layer);
    }

    /**
     *
     * @param layers
     */
    public void setLayers(ArrayList<Layer> layers) {
        this.layers = layers;
    }

    public void undo() {
        throw new UnsupportedOperationException();
    }

    public void redo() {
        throw new UnsupportedOperationException();
    }

    /**
     *
     * @param ids
     */
    public void mergeLayers(int[] ids) {
        throw new UnsupportedOperationException();
    }

    /**
     *
     * @param ids
     */
    public void groupLayers(int[] ids) {
        throw new UnsupportedOperationException();
    }

    /**
     *
     * @param ids
     * @return
     */
    public Selection intersect(int[] ids) {
        throw new UnsupportedOperationException();
    }

    public void createNewProject(int width, int height) {
        layers = new ArrayList<>();
        GUIMgr.clearDrawingArea();
    }
    Tool drawingtool = null;
    boolean drawing = false;
    boolean moving = false;

    public void startDrawing(int x, int y, Tool tool) {
        drawingtool = tool;
        drawing = true;
        selectedlayer.startDrawing(x, y, tool);
    }

    public void mouseOffset(int ox, int oy) {
        if (drawing) {
            java.awt.Rectangle prect = selectedlayer.getRect();
            selectedlayer.mouseOffset(ox, oy);
            java.awt.Rectangle nrect = selectedlayer.getRect();
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    if (prect.contains(x, y) || nrect.contains(x, y)) {
                        for (Layer layer : layers) {
                            Color col = layer.getPixel(x, y);
                            if (col.getAlpha() == 255) {
                                // TODO: implement color blending
                                GUIMgr.setPixel(x, y, col);
                                break;
                            }
                        }
                    }
                }
            }
        } else if (moving) {
            
        }
    }

    public void finishDrawing() {
        selectedlayer.finishDrawing();
        drawing = false;
    }

    public void finishMoving() {
        moving = false;
    }
}
