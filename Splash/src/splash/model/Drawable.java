package splash.model;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.HashMap;

public interface Drawable {

    /**
     *
     * @param target
     * @return
     */
    BufferedImage getBitmap();

    HashMap<String, Class<?>> getEditableList();

    /**
     *
     * @param modrec
     * @return
     */
    Error[] updateProperties(HashMap<String, Object> modrec);

    /**
     *
     * @param val
     */
    int getWidth();

    int getHeight();

    void setWidth(int val);

    void setHeight(int val);        

    Point getCenter();

    State getState();

    State updateState();
    
    void startDrawing(Point start);
    void mouseOffset(Point offset);
    void finishDrawing();
}
