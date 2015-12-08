/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package splash.model;

import java.awt.Color;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.awt.Rectangle;

/**
 *
 * @author Hesham
 */
public abstract class Layer {

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

    Color getPixel(int x, int y) {
        return new Color(bitmap.getRGB(x, y), true);
    }

    BufferedImage getBitmap() {
        return bitmap;
    }
}
