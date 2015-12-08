package splash.model;

import java.awt.Color;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.lang.reflect.Type;
import java.util.HashMap;
import javafx.scene.canvas.GraphicsContext;

public abstract class Object2D implements Drawable {

    private Point size;
    Color color;
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
    public State getState() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public State updateState() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public abstract BufferedImage getBitmap();

    /**
     * @return the width
     */
    public int getWidth() {
        return width;
    }

    /**
     * @param width the width to set
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * @return the height
     */
    public int getHeight() {
        return height;
    }

    /**
     * @param height the height to set
     */
    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public Point getCenter() {
        return new Point(getWidth() / 2, getHeight() / 2);
    }
    
    
    // Realtime drawing
    
    Point dstart = null;
    Point cpos = null;

    @Override
    public void startDrawing(Point start) {
        dstart = cpos = start;
    }

    @Override
    public void mouseOffset(Point offset) {
        if (dstart != null) {
            cpos.move(offset.x, offset.y);
            setWidth(Math.abs(cpos.x - dstart.x));
            setHeight(Math.abs(cpos.y - dstart.y));
        }
    }

    @Override
    public void finishDrawing() {
        dstart = cpos = null;
    }
    
    public Point getDrawingStartPoint()
    {
        return dstart;
    }
    public Point getCurrentDrawingPos()
    {
        return cpos;
    }
}
