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
    public abstract void transform(Point diff);

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
        java.awt.Color awcol = new java.awt.Color(bitmap.getRGB(sx - x, sy - y), true);
        int r, g, b, a;
        r = awcol.getRed();
        g = awcol.getGreen();
        b = awcol.getBlue();
        a = awcol.getAlpha();
        return new Color(r / 255.0, g / 255.0, b / 255.0, a / 255);
    }

    int getX() {
        return x;
    }

    int getY() {
        return y;
    }

    void setX(int val) {
        x = val;
    }

    void setY(int val) {
        y = val;
    }

    BufferedImage getBitmap() {
        return bitmap;
    }
}
