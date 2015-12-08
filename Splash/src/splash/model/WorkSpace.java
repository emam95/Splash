package splash.model;

import java.util.ArrayList;
import java.util.Stack;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
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
        layers.add(selectedlayer = layer);
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
    Tool drawingtool = null;
    boolean drawing = false;
    boolean moving = false;
    ObjectLayer activelayer;

    public void startDrawing(int x, int y, Tool tool, Color col) {
        drawingtool = tool;
        drawing = true;
        tool.startDrawing(x, y, col);
    }

    public void mouseMoved(int ox, int oy) {
        if (drawing) {
            java.awt.Rectangle prect = selectedlayer.getRect();
            //System.out.println("P"+prect.getX() + "," + prect.getY() + "|" + prect.getWidth() + "," + prect.getHeight());
            drawingtool.mouseMoved(ox, oy);
            java.awt.Rectangle nrect = selectedlayer.getRect();
            //System.out.println("N"+nrect.getX() + "," + nrect.getY() + "|" + nrect.getWidth() + "," + nrect.getHeight());
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    if (prect.contains(x, y) || nrect.contains(x, y)) {
                        for (Layer layer : layers) {
                            if (layer.getRect().contains(x, y)) {
                                Color col = layer.getPixel(x, y);
                                double op = col.getOpacity();
                                if (Math.abs(col.getOpacity() - 1) < 1e-9) {
                                    // TODO: implement color blending
                                    GUIMgr.setPixel(x, y, col);
                                    break;
                                }
                            }
                            GUIMgr.clearPixel(x, y);
                        }
                    }
                }
            }
        } else if (moving) {

        }
    }

    public void finishDrawing() {
        drawingtool.finishDrawing();
        drawing = false;
    }

    public void finishMoving() {
        moving = false;
    }

    public boolean isDrawing() {
        return drawing;
    }
}
