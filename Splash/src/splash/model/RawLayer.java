/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package splash.model;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javafx.scene.paint.Color;
import splash.controller.GUIMgr;

/**
 *
 * @author Hesham
 */
public class RawLayer extends Layer {

    public RawLayer() {
        super();
        bitmap = new BufferedImage(GUIMgr.getWorkSpace().getWidth(), GUIMgr.getWorkSpace().getHeight(), BufferedImage.TYPE_INT_ARGB);
    }

    @Override
    public void resizeX(int newwidth) {
    }

    @Override
    public void resizeY(int newheight) {
    }

    @Override
    public void transform(Point diff) {
    }

    @Override
    void addWidthRel(int dif, int i) {
    }

    @Override
    void addHeightRel(int dif, int i) {
    }

    public Object[] getARGB() {
        ArrayList<Integer> argbtable = new ArrayList<>();
        for (int x = 0; x < getWidth(); x++) {
            for (int y = 0; y < getHeight(); y++) {
                argbtable.add(getPixel(x, y).getRGB());
            }
        }
        return argbtable.toArray();
    }

    public void setARGB(Object[] argbs) {
        for (int x = 0; x < getWidth(); x++) {
            for (int y = 0; y < getHeight(); y++) {
                int argb = (int) argbs[y * getWidth() + x];
                setPixel(x, y, argb);//new Color(argb&0xff000000,argb&0xff0000,argb&0xff00,argb&0xff));
            }
        }
    }
}
