/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package splash.model;

import java.awt.image.BufferedImage;
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
    public void rotate(float rad) {        
    }

    @Override
    public void erase(Point target) {        
    }

    @Override
    void addWidthRel(int dif, int i) {        
    }

    @Override
    void addHeightRel(int dif, int i) {        
    }
}
