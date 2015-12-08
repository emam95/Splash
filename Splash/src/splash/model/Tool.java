package splash.model;

import java.awt.Image;
import javafx.scene.paint.Color;

;

public abstract class Tool {

    private Image thumb;
    protected String id;

    /**
     *
     * @param startpoint
     */
    public abstract void initFunction(Point startpoint);

    public String getId() {
        return this.id;
    }

    public abstract void startDrawing(int x, int y, Color col);

    public abstract void mouseMoved(int x, int y);

    public abstract void finishDrawing();
}
