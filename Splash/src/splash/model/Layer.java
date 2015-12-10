/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package splash.model;

import java.awt.image.BufferedImage;
import java.awt.Rectangle;
import javafx.scene.paint.Color;

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

    public abstract void undo();

    public abstract void redo();

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

    /**
     *
     * @param rad
     */
    public abstract void rotate(float rad);

    /**
     *
     * @param target
     */
    public abstract void erase(Point target);

    /**
     *
     * @param point
     * @return
     */
    public abstract Selection getSelection(Point point);

    public Rectangle getRect() {
        return new Rectangle(0, 0, bitmap.getWidth(), bitmap.getHeight());
    }

    Color getPixel(int sx, int sy) {
        if (bitmap == null) {
            return null;
        }
        java.awt.Color awcol = new java.awt.Color(bitmap.getRGB(sx - getX(), sy - getY()), true);
        int r, g, b, a;
        r = awcol.getRed();
        g = awcol.getGreen();
        b = awcol.getBlue();
        a = awcol.getAlpha();
        return new Color(r / 255.0, g / 255.0, b / 255.0, a / 255);
    }
    int ax = 0, ay = 0;

    void adjustX(int i) {
        ax = i;        
    }

    void adjustY(int i) {        
        ay = i;
    }

    int getX() {
        return x + ax;
    }

    int getY() {
        return y + ay;
    }

    void applyAdjustedPos() {
        x += ax;
        ax = 0;
        y += ay;
        ay = 0;
    }

    void setX(int val) {
        x = val;
    }

    void setY(int val) {
        y = val;
    }

    public BufferedImage getBitmap() {
        return bitmap;
    }

    public void transformTo(Point p) {
        transform(p.subtract(x, y));
    }
}
