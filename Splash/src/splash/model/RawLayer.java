/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package splash.model;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
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

    public int[] getARGB() {
        int[] argbs = new int[getWidth() * getHeight()];
        for (int i = 0; i < getWidth(); i++) {
            for (int j = 0; j < getHeight(); j++) {
                argbs[j * getWidth() + j] = (getPixel(i, j).getRGB());
            }
        }
        return argbs;
    }

    public void setARGB(Object[] argbs) {
        for (int i = 0; i < getWidth(); i++) {
            for (int j = 0; j < getHeight(); j++) {
                int argb = (int) argbs[j * getWidth() + j];
                setPixel(i, j, argb);//new Color(argb&0xff000000,argb&0xff0000,argb&0xff00,argb&0xff));
            }
            System.out.println("at row " + i);
        }
    }

    @Override
    public HashMap<String, Property> getEditableList() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
