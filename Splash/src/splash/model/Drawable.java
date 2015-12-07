package splash.model;

import java.awt.Point;
import java.lang.reflect.Type;
import java.util.HashMap;

public interface Drawable {

    /**
     *
     * @param target
     */
    void draw(WorkSpace target);

    HashMap<String, Type> getEditableList();

    /**
     *
     * @param modrec
     * @return
     */
    Error[] updateProperties(HashMap<String, Object> modrec);

    Point getTopLeft();

    Point getSize();

    /**
     *
     * @param val
     */
    void setTopLeft(Point val);

    /**
     *
     * @param val
     */
    void setSize(Point val);

    Point getCener();

    State getState();

    State updateState();

}
