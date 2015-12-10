package splash.model;

import java.awt.Image;
import javafx.scene.paint.Color;

;

public abstract class Tool {

    static Tool lastselected = null;
    private Image thumb;
    protected String id;    


    public String getId() {
        return this.id;
    }

    public void select() {
        if (lastselected != null) {
            lastselected.unselect();
        }
        lastselected = this;
        System.out.println(id + " selected");
    }

    public void unselect() {
        System.out.println(id + " unselected");
    }

    public void notifyLayerChanged() {
    }

    public abstract void primaryKey(int x, int y, Color col);

    public abstract void mouseMoved(int x, int y);

    public abstract void secKey();
}
