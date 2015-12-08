package splash.model;

import java.awt.Image;
import java.awt.Point;

public abstract class Tool {

    private Image thumb;

    /**
     *
     * @param startpoint
     */
    public abstract void initFunction(Point startpoint);

    public abstract void startDrawing(int x, int y);

    public abstract void mouseOffset(int x, int y);

    public abstract void finishDrawing();
}
