package splash.model;

import java.util.HashMap;
import javafx.scene.paint.Color;

public class ObjectLayer extends Layer {

    private final Object2D content;
    private int id;

    public ObjectLayer(Object2D drawable) {
        content = drawable;
        content.setParent(this);
    }

    public void redraw() {
        bitmap = content.getBitmap();
    }

    public Drawable getObj() {
        return content;
    }

    @Override
    public void resizeX(int newwidth) {
        content.setWidth(newwidth);
        redraw();
    }

    @Override
    public void resizeY(int newheight) {
        content.setHeight(newheight);
        redraw();
    }




    Point startp, cpoint;

    public void primaryKey(int x, int y, Color col) {
        content.primaryKey(new Point(x, y), col);
        redraw();
    }

    public boolean isDrawing() {
        return content.isdrawing();
    }

    public void mouseMoved(int x, int y) {
        if (isDrawing()) {
            content.mouseMoved(new Point(x, y));
            redraw();
        }
    }

    public void secKey() {
        content.secKey();
        redraw();
    }

    public java.awt.Rectangle getRect() {
        boolean bmpe = bitmap != null;
        return new java.awt.Rectangle(getX(), getY(), bmpe ? bitmap.getWidth() : 0, bmpe ? bitmap.getHeight() : 0);
    }

    Point getPos() {
        return new Point(getX(), getY());
    }

    @Override
    void addWidthRel(int dif, int i) {
        int w = getWidth();
        int nw = w + dif;
        if (nw < 0) {
            content.mirrorX();
            nw *= -1;
            resizeX(nw);
            if (i == 0 || i == 7 || i == 6) {
                setX(getX() + w);
            } else {
                setX(getX() - getWidth());
            }
            mirroredx = true;
        } else {
            resizeX(nw);
            if (i == 0 || i == 7 || i == 6) {
                dif = getWidth() - w; // floating point inaccuracy can accumulate
                setX(getX() - dif);
            }
        }
    }

    @Override
    void addHeightRel(int dif, int i) {
        int h = getHeight();
        int nh = h + dif;
        if (nh < 0) {
            content.mirrorY();
            nh *= -1;
            resizeY(nh);
            if (i == 0 || i == 1 || i == 2) {
                setY(getY() + h);
            } else {
                setY(getY() - getHeight());
            }
            mirroredy = true;
        } else {
            resizeY(nh);
            if (i == 0 || i == 1 || i == 2) {
                dif = getHeight() - h; // floating point inaccuracy can accumulate
                setY(getY() - dif);
            }
        }
    }

    @Override
    public HashMap<String, Property> getEditableList() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public Object2D getObject(){
        return content;
    }
}
