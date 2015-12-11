package splash.model;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import javafx.scene.paint.Color;

public abstract class Object2D implements Drawable {
    ObjectLayer parent = null;
    boolean wasdrawn = false;
    private Color color;
    int width;
    int height;
    private boolean lsa = false;

    @Override
    public HashMap<String, Object> getEditableList() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Error[] updateProperties(HashMap<String, Object> modrec) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public abstract BufferedImage getBitmap();

    /**
     * @return the width
     */
    public int getWidth() {
        return Math.max(1, width);
    }

    /**
     * @param width the width to set
     */
    @Override
    public void setWidth(int width) {
        this.width = Math.max(1, width);
    }

    /**
     * @return the height
     */
    @Override
    public int getHeight() {
        return Math.max(1, height);
    }

    /**
     * @param height the height to set
     */
    @Override
    public void setHeight(int height) {
        this.height = Math.max(1, height);
    }

    @Override
    public PointF getCenter() {
        return new PointF(getWidth() / 2f, getHeight() / 2f);
    }

    // Realtime drawing
    Point dstart = null;
    Point cpos = null;
    boolean isdrawing = false;

    @Override
    public void primaryKey(Point start, Color col) {
        if (isdrawing) {
            isdrawing = false;            
            return;
        }
        isdrawing = true;
        dstart = cpos = start;
        setColor(col);        
    }

    @Override
    public void mouseMoved(Point newpos) {
        if (isdrawing) {
            cpos = newpos;
            setWidth(Math.abs(cpos.getX() - dstart.getX()));
            setHeight(Math.abs(cpos.getY() - dstart.getY()));
        }
    }

    @Override
    public void secKey() {
        isdrawing = false;
        wasdrawn = true;
        if(parent!=null)
            parent.applyAdjustedPos();
    }

    public Point getDrawingStartPoint() {
        return dstart;
    }

    public Point getCurrentDrawingPos() {
        return cpos;
    }

    /**
     * @return the color
     */
    public Color getColor() {
        return color;
    }

    /**
     * @param color the color to set
     */
    public void setColor(Color color) {
        this.color = color;
    }

    boolean isdrawing() {
        return isdrawing;
    }

    boolean wasDrawn() {
        return wasdrawn;
    }

    void setParent(ObjectLayer aThis) {
        parent = aThis;
    }
}
