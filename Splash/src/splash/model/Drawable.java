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

    PointF getCenter();

    void primaryKey(Point start, Color col);

    void mouseMoved(Point offset);

    void secKey();
}
