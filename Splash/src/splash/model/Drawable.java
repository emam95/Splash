package splash.model;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import javafx.scene.paint.Color;

public interface Drawable {

    /**
     *
     * @param target
     * @return
     */
    BufferedImage getBitmap();

    HashMap<String, Property> getEditableList();

    /**
     *
     * @param val
     */
    int getWidth();

    int getHeight();

    void setWidth(int val);

    void setHeight(int val);

    PointF getCenter();

    void primaryKey(Point start, Color col);

    void mouseMoved(Point offset);

    void secKey();

    public void drawToBitmap(BufferedImage target, int x, int y);
}
