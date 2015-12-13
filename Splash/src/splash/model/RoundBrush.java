/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package splash.model;

import java.awt.image.BufferedImage;
import javafx.scene.paint.Color;

/**
 *
 * @author Hesham
 */
class RoundBrush extends Brush {

    static int defaultstrokesize = 10;
    static Color defaultcolor = Color.BLACK;
    Drawable stroke;

    public RoundBrush() {
        this(defaultstrokesize, defaultcolor);
    }

    public RoundBrush(int rad, Color color) {
        stroke = new Ellipse();
        stroke.setWidth(rad * 2);
        stroke.setHeight(rad * 2);
        this.color = color;
        if (stroke instanceof Object2D) {
            ((Object2D) stroke).setFillColor(color);
        }
    }

    @Override
    public void drawTo(BufferedImage target, int x, int y) {
        stroke.drawToBitmap(target, x, y);
    }

    @Override
    public int getWidth() {
        return stroke.getWidth();
    }

    @Override
    public int getHeight() {
        return stroke.getWidth();
    }
}
