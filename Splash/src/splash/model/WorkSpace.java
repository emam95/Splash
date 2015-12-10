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
    private Selection selection = null;

    public WorkSpace(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public GraphicsContext getGraphics() {
        return this.graphics;
    }
    
    public void selectLayer(int id){
        for(Layer l : layers)
        {
            if(l.getId() == id)
                selectedlayer = l;
        } 
    }

    public Layer getSelectedLayer() {
        return selectedlayer;
    }

    public Layer[] getLayers() {
        Layer[] ar = new Layer[layers.size()];
        return this.layers.toArray(ar);
    }

    public void addLayer(Layer layer) {
        layers.addFirst(selectedlayer = layer);
        redrawRegion(layer.getRect(), null, true);
    }

    public void removeLayer(int id) {
        if (selectedlayer.getId() == id) {
            selectedlayer = null;
        }
        for (Layer layer : layers) {
            if (layer.getId() == id) {
                layers.remove(layer);
                redrawRegion(layer.getRect(), null, true);
            }
        }
    }

    /**
     *
     * @param layers
     */
    public void setLayers(LinkedList<Layer> layers) {
        this.layers = layers;
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
    ObjectLayer activelayer;
    long redrawrestraint = 50;
    long lastmousemove = 0;

    //Thread th;
    public void redrawRegion(java.awt.Rectangle prect, java.awt.Rectangle srect, boolean primaryrectonly) {
        /*  if (th!=null && th.isAlive()) {
            return;
        }
        (th=new Thread(() -> {*/
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                if (prect.contains(x, y) || (!primaryrectonly && srect.contains(x, y))) {
                    boolean found = false;
                    for (Layer layer : layers) {
                        if (layer.getRect().contains(x, y)) {
                            Color col = layer.getPixel(x, y);
                            if (col != null && col.getOpacity() == 1) {
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
        //})).start();
    }

    public void primaryKey(int x, int y, Tool tool, Color col) {
        drawingtool = tool;
        drawing = true;
        tool.primaryKey(x, y, col);
    }

    public void mouseMoved(int ox, int oy) {
        if (drawing && selectedlayer != null) {
            if (System.currentTimeMillis() - lastmousemove < redrawrestraint) {
                return;
            }
            lastmousemove = System.currentTimeMillis();
            SW.start();
            final java.awt.Rectangle prect = selectedlayer.getRect();
            drawingtool.mouseMoved(ox, oy);
            final java.awt.Rectangle nrect = selectedlayer.getRect();
            redrawRegion(prect, nrect, false);
            redrawrestraint = SW.getElapsed() * 2;
        }
    }

    public void secKey() {
        drawingtool.secKey();
        drawing = false;
    }

    public boolean isDrawing() {
        return drawing;
    }
}
