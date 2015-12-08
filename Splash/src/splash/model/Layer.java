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

    public Layer(int id) {
    }

    public abstract void undo();

    public abstract void redo();

    public int getId() {
        return this.id;
    }

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

    public abstract void startDrawing(int x, int y, Tool tool);

    public abstract void mouseOffset(int x, int y);

    public abstract void finishDrawing();

    public Rectangle getRect() {
        return new Rectangle(0, 0, bitmap.getWidth(), bitmap.getHeight());
    }

    Color getPixel(int x, int y) {
        return new Color(bitmap.getRGB(x, y), true);
    }
}
