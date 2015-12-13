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
    public void addWidthRel(int dif, int i) {
    }

    @Override
    public void addHeightRel(int dif, int i) {
    }

    public SparseArray getARGB() {
        SparseArray ar = new SparseArray();
        for (int i = 0; i < getWidth(); i++) {
            for (int j = 0; j < getHeight(); j++) {
                ar.add(getPixel(i, j).getRGB());
            }
        }
        return ar;
    }

    public void setARGB(SparseArray ar) {
        for (int i = 0; i < getWidth(); i++) {
            for (int j = 0; j < getHeight(); j++) {
                int argb = ar.get(j * getWidth() + j);
                setPixel(i, j, argb);//new Color(argb&0xff000000,argb&0xff0000,argb&0xff00,argb&0xff));
            }            
        }
    }

    @Override
    public HashMap<String, Property> getEditableList() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
