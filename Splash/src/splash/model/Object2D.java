package splash.model;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import javafx.scene.paint.Color;

public abstract class Object2D implements Drawable {

    private boolean wasdrawn;
    private int fillcolor;
    private int canvascolor;
    private boolean isFilled;    
    private ObjectLayer parent;
    int width;
    int height;

    public Object2D() {
        isFilled = true; // for testing
        nullifyfill = false;
        parent = null;
    }

    @Override
    public BufferedImage getBitmap() {
        BufferedImage output = new BufferedImage(getWidth() + 1, getHeight() + 1, BufferedImage.TYPE_INT_ARGB);
        drawToBitmap(output, 0, 0);
        return output;
    }

    /**
     * @return the width
     */
    @Override
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

    // Temporary variables
    public Point dstart = null;
    public Point cpos = null;
    public boolean isdrawing = false;
    public boolean nullifyfill;
    
    @Override
    public void primaryKey(Point start, Color col) {
        if (isdrawing) {
            isdrawing = false;
            return;
        }
        isdrawing = true;
        dstart = cpos = start;
        setCanvascolor(Helper.getARGB(col));
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
        setWasdrawn(true);
        if (getParent() != null) {
            getParent().applyAdjustedPos();
        }
    }

    public Point getDrawingStartPoint() {
        return dstart;
    }

    public Point getCurrentDrawingPos() {
        return cpos;
    }

    boolean isdrawing() {
        return isdrawing;
    }

    boolean wasDrawn() {
        return getWasdrawn();
    }

    void setParent(ObjectLayer aThis) {
        parent = aThis;
    }

    public boolean getIsFilled() {
        return this.isIsFilled();
    }

    /**
     *
     * @param isFilled
     */
    public void setIsFilled(boolean isFilled) {
        this.isFilled = isFilled;
    }

    @Override
    public HashMap<String, Property> getEditableList() {
        return new HashMap<String, Property>() {
            {
                put("FillColor", new Property<Integer>() {
                    @Override
                    public Integer get() {
                        return getFillcolor();
                    }

                    @Override
                    public void set(Integer val) {
                        setFillcolor(val);
                    }
                });
                put("CanvasColor", new Property<Integer>() {
                    @Override
                    public Integer get() {
                        return getCanvascolor();
                    }

                    @Override
                    public void set(Integer val) {
                        setCanvascolor(val);
                    }
                });
                put("Width", new Property<Integer>() {
                    @Override
                    public Integer get() {
                        return getWidth();
                    }

                    @Override
                    public void set(Integer val) {
                        setWidth(val);
                    }
                });
                put("Height", new Property<Integer>() {
                    @Override
                    public Integer get() {
                        return getHeight();
                    }

                    @Override
                    public void set(Integer val) {
                        setHeight(val);
                    }
                });
            }
        };
    }

    void mirrorX() {
    }

    void mirrorY() {
    }

    /**
     * @return the wasdrawn
     */
    public boolean getWasdrawn() {
        return wasdrawn;
    }

    /**
     * @param wasdrawn the wasdrawn to set
     */
    public void setWasdrawn(boolean wasdrawn) {
        this.wasdrawn = wasdrawn;
    }

    /**
     * @return the canvascolor
     */
    public int getCanvascolor() {
        return canvascolor;
    }

    /**
     * @param canvascolor the canvascolor to set
     */
    public void setCanvascolor(int canvascolor) {
        this.canvascolor = canvascolor;
    }

    /**
     * @return the isFilled
     */
    public boolean isIsFilled() {
        return isFilled;
    }

    /**
     * @return the fillcolor
     */
    public int getFillcolor() {
        return fillcolor;
    }

    /**
     * @param fillcolor the fillcolor to set
     */
    public void setFillcolor(int fillcolor) {
        this.fillcolor = fillcolor;
    }

    /**
     * @return the parent
     */
    public ObjectLayer getParent() {
        return parent;
    }
}
