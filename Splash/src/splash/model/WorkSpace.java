package splash.model;

import java.awt.Rectangle;
import java.util.ArrayList;
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
    private Selection selection = new Selection();

    public WorkSpace(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public GraphicsContext getGraphics() {
        return this.graphics;
    }

    public void selectLayer(int id) {
        for (Layer l : layers) {
            if (l.getId() == id) {
                selectedlayer = l;
                OnSelectedLayerChanged();
            }
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
        redrawRegion(layer.getRect(), null);
    }

    public void removeLayer(int id) {
        if (selectedlayer != null && selectedlayer.getId() == id) {
            selectedlayer = null;
            OnSelectedLayerChanged();
        }
        for (Layer layer : layers) {
            if (layer.getId() == id) {
                layers.remove(layer);
                redrawRegion(layer.getRect(), null);
                break;
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
    public void redrawRegion(java.awt.Rectangle prect, java.awt.Rectangle srect) {
        if (supnred) {
            supnred = false;
            return;
        }
        /*  if (th!=null && th.isAlive()) {
            return;
        }
        (th=new Thread(() -> {*/
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                if (prect.contains(x, y) || (srect != null && srect.contains(x, y))) {
                    GUIMgr.setPixel(x, y, getPixel(x, y, false));
                }
            }
            //})).start();
        }
    }

    public void primaryKey(int x, int y, Tool tool, Color col) {
        drawingtool = tool;
        drawing = true;
        tool.primaryKey(x, y, col);
    }
    boolean supnred = false;

    public void supressNextRedraw() {
        supnred = true;
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
            redrawRegion(prect, nrect);
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

    public Color getPixel(int x, int y, boolean ignoreselection) {
        float a = 0, r = 0, g = 0, b = 0;
        if (!ignoreselection && selection != null && selection.getRect().contains(x, y)) {
            Color col = selection.getPixel(x, y);
            if (col != null && col.getOpacity() > 0) {
                return col;
            }
        }
        for (Layer layer : layers) {
            if (layer.contains(x, y)) {
                java.awt.Color col = layer.getPixel(x, y);
                float af = 0;
                if (col != null && (af = col.getAlpha()) > 0) {
                    r = Math.min(255, (((col.getRed() * af + r * a) * 255) % 256) / 255f);
                    g = Math.min(255, (((col.getGreen() * af + g * a) * 255) % 256) / 255f);
                    b = Math.min(255, (((col.getBlue() * af + b * a) * 255) % 256) / 255f);
                    a = Math.min(255, af / 255f + a / 255f - af / 255f * a / 255f);
                    if (a == 1) {
                        break;
                    }
                }
            }
        }
        return new Color(r, g, b, a);
    }

    Selection getSelection() {
        return selection;
    }
    ArrayList<LayerChangedEventHandler> selectedlayerchangedevents = new ArrayList<>();

    public void setOnSelectedLayerChanged(LayerChangedEventHandler handler) {
        if (!selectedlayerchangedevents.contains(handler)) {
            selectedlayerchangedevents.add(handler);
        }
    }

    public void removeOnSelectedLayerChanged(LayerChangedEventHandler handler) {
        if (selectedlayerchangedevents.contains(handler)) {
            selectedlayerchangedevents.remove(handler);
        }
    }

    public void OnSelectedLayerChanged() {
        for (LayerChangedEventHandler handler : selectedlayerchangedevents) {
            handler.selectedLayerChanged(selectedlayer);
        }
    }

    void setSelection(Selection sel) {
        Rectangle orect = selection.getRect();
        Rectangle nrect = sel.getRect();
        selection = sel;
        redrawRegion(orect, nrect);
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }
}
