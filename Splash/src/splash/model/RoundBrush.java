/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package splash.model;

import java.awt.image.BufferedImage;

/**
 *
 * @author Hesham
 */
class RoundBrush extends Brush {

    static int defaultstrokesize = 10;
    Drawable stroke;

    public RoundBrush() {
        this(defaultstrokesize);
    }

    public RoundBrush(int rad) {
        stroke = new Ellipse();
        stroke.setWidth(rad * 2);
        stroke.setHeight(rad * 2);
    }

    @Override
    public void drawTo(BufferedImage target, int x, int y) {
        stroke.drawToBitmap(target, x, y);
    }
}
