package splash.model;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import javafx.scene.paint.Color;

public abstract class Object2D implements Drawable {

    ObjectLayer parent = null;
    boolean wasdrawn = false;
    private int fillcolor;
    private int canvascolor = 0xff000000;
    private boolean isFilled = true;
    boolean nullifyfill = false;
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
        setFillColor(Helper.getARGB(col));
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
        if (parent != null) {
            parent.applyAdjustedPos();
        }
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
    public int getFillColor() {
        return fillcolor;
    }

    /**
     * @param color the color to set
     */
    public void setFillColor(int color) {
        this.fillcolor = color;
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

    public boolean getIsFilled() {
        return this.isFilled;
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
                put("Fillint", new Property<Integer>() {
                    @Override
                    public Integer get() {
                        return getFillColor();
                    }

                    @Override
                    public void set(Integer val) {
                        setFillColor(val);
                    }
                });
                put("CanvasInteger", new Property<Integer>() {
                    @Override
                    public Integer get() {
                        return getCanvasColor();
                    }

                    @Override
                    public void set(Integer val) {
                        setCanvasColor(val);
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
     * @return the canvascolor
     */
    public int getCanvasColor() {
        return canvascolor;
    }

    /**
     * @param canvascolor the canvascolor to set
     */
    public void setCanvasColor(int canvascolor) {
        this.canvascolor = canvascolor;
    }
}
