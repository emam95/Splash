package splash.model;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Stack;
import javafx.scene.canvas.GraphicsContext;

public class WorkSpace {
    private BufferedImage bitmap = null; // untill a new project is created
    private GraphicsContext graphics;
    private ArrayList<Layer> layers;
    private Stack<State> paststates;
    private Stack<State> futstates;
    private Selection selection;

    public GraphicsContext getGraphics() {
        return this.graphics;
    }

    public ArrayList<Layer> getLayers() {
        return this.layers;
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

    public BufferedImage getBitmapToDraw()
    {
        return bitmap;
    }
    
    public void createNewProject(int width,int height)
    {
        bitmap = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        layers = new ArrayList<>();
        splash.controller.GUIMgr.notifyRegionUpdate(0, 0, width, height);
    }
}
