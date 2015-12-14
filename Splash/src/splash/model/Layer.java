/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package splash.model;

import java.awt.image.BufferedImage;
import java.awt.Rectangle;
import java.util.HashMap;
import javafx.scene.paint.Color;
import splash.controller.GUIMgr;

/**
 *
 * @author Hesham
 */
public abstract class Layer {

    private int x = 0, y = 0;
    BufferedImage bitmap;
    private int id;

    public Layer() {
        setId(idseed++);
    }

    public int getId() {
        return this.id;
    }
    static int idseed = 0;

    /**
     *
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    public abstract void resizeX(int newwidth);

    public abstract void resizeY(int newheight);

    /**
     *
     * @param diff
     */
    public void transform(Point diff) {
        x += diff.getX();
        y += diff.getY();
    }

    public Rectangle getRect() {
        return new Rectangle(getX(), getY(), getWidth(), getHeight());
    }

    java.awt.Color getPixel(int sx, int sy) {
        if (bitmap == null) {
            return null;
        }
        return new java.awt.Color(bitmap.getRGB(sx - getX(), sy - getY()), true);
    }
    int ax = 0, ay = 0;

    public void adjustX(int i) {
        ax = i;
    }

    public void adjustY(int i) {
        ay = i;
    }

    public int getX() {
        return x + ax;
    }

    public int getY() {
        return y + ay;
    }

    public void applyAdjustedPos() {
        x += ax;
        ax = 0;
        y += ay;
        ay = 0;
    }

    public void setX(int val) {
        x = val;
    }

    public void setY(int val) {
        y = val;
    }

    public BufferedImage getBitmap() {
        return bitmap;
    }

    public void transformTo(Point p) {
        transform(p.subtract(getX(), getY()));
    }

    public void transform(int xshift, int yshift) {
        x += xshift;
        y += yshift;
    }

    Point getPos() {
        return new Point(getX(), getY());
    }

    public int getWidth() {
        if (bitmap != null) {
            return bitmap.getWidth();
        }
        return 0;
    }

    int getHeight() {
        if (bitmap != null) {
            return bitmap.getHeight();
        }
        return 0;
    }

    abstract public void addWidthRel(int dif, int i);

    abstract public void addHeightRel(int dif, int i);

    boolean mirroredx = false;
    boolean mirroredy = false;

    // Special function for resize tool. One time use boolean.
    public boolean wasMirroredX() {
        if (mirroredx) {
            mirroredx = false;
            return true;
        } else {
            return false;
        }
    }

    public boolean wasMirroredY() {
        if (mirroredy) {
            mirroredy = false;
            return true;
        } else {
            return false;
        }
    }

    boolean contains(int x, int y) {
        int tx = getX(), ty = getY(), w = getWidth(), h = getHeight();
        return x >= tx && x < tx + w && y >= ty && y < ty + h;
    }

    public void stroke(Brush brush, int x, int y) {
        brush.drawTo(bitmap, x - getX(), y - getY());
        GUIMgr.getWorkSpace().redrawRegion(new Rectangle(x, y, brush.getWidth(), brush.getHeight()), null);
        GUIMgr.getWorkSpace().supressNextRedraw();;
    }

    public void setPixel(int x, int y, int argb) {
        bitmap.setRGB(x, y, argb);
    }
    
    public PointF getCenter() {
        return new PointF(getWidth() / 2f, getHeight() / 2f);
    }

    public abstract HashMap<String, Property> getEditableList();
}
