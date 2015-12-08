package splash.model;

import java.util.LinkedList;
import java.util.Stack;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import splash.controller.GUIMgr;

public class WorkSpace {

    int width, height;
    private GraphicsContext graphics;
    private LinkedList<Layer> layers = new LinkedList<>();
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

    public Layer[] getLayers() {
        Layer[] ar = new Layer[layers.size()];
        return this.layers.toArray(ar);
    }

    public void addLayer(Layer layer) {
        layers.addFirst(selectedlayer = layer);
    }

    /**
     *
     * @param layers
     */
    public void setLayers(LinkedList<Layer> layers) {
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
    Thread th = null;
    boolean lock = false;

    public void mouseMoved(int ox, int oy) {
        if (drawing) {
            if (lock) {
                return;
            }            
            lock = true;
            (th = new Thread() {
                @Override
                public void run() {
                    java.awt.Rectangle prect = selectedlayer.getRect();
                    drawingtool.mouseMoved(ox, oy);
                    java.awt.Rectangle nrect = selectedlayer.getRect();
                    for (int x = 0; x < width; x++) {
                        for (int y = 0; y < height; y++) {
                            if (prect.contains(x, y) || nrect.contains(x, y)) {
                                boolean found = false;
                                for (Layer layer : layers) {
                                    if (layer.getRect().contains(x, y)) {
                                        Color col = layer.getPixel(x, y);
                                        if (col.getOpacity() == 1) {
                                            // TODO: implement color blending
                                            GUIMgr.setPixel(x, y, col);
                                            found = true;
                                            break;
                                        }
                                    }
                                }
                                if (!found) {
                                    GUIMgr.clearPixel(x, y);
                                }
                            }
                        }
                    }
                    System.out.println("Drew");
                    lock = false;
                }
            }).start();
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
