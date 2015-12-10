package splash.model;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import javafx.scene.paint.Color;

public abstract class Object2D implements Drawable {

    private Point size;
    private Color color;
    private boolean isFilled;
    int width;
    int height;

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
    public HashMap<String, Class<?>> getEditableList() {
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

    @Override
    public void startDrawing(Point start, Color col) {
        dstart = cpos = start;
        setColor(col);
    }

    @Override
    public void mouseMoved(Point newpos) {
        if (dstart != null) {
            cpos = newpos;
            setWidth(Math.abs(cpos.getX() - dstart.getX()));
            setHeight(Math.abs(cpos.getY() - dstart.getY()));
        }
    }

    @Override
    public void finishDrawing() {
        dstart = cpos = null;
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
}
