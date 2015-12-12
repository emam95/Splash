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
    
    @Override
    public BufferedImage getBitmap() {
        BufferedImage output = new BufferedImage(getWidth() + 1, getHeight() + 1, BufferedImage.TYPE_INT_ARGB);
        drawToBitmap(output, 0, 0);
        return output;
    }

    private boolean lsa = false;
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

    void mirrorX() {}
    void mirrorY() {}
}
