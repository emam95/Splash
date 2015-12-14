/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package splash.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author MEmam
 */
public class LayerGroup extends Layer{
    
    List<Layer> layers = new ArrayList<Layer>();
    
    public void addLayer(Layer l)
    {
        layers.add(l);
    }
    
    @Override
    public void transform(Point diff) {
        for(Layer l : layers)
        {
            l.setX(l.getX() + diff.getX());
            l.setY(l.getY() + diff.getY());
        }
    }

    @Override
    public void resizeX(int val) {
        float cx = getCenter().getX();
        float rf = (float) val / getWidth();
        for(Layer l : layers)
        {
            float nx = l.getX() - cx;
            nx *= rf;
            l.setX((int) (nx + val / 2f));
        }
        //width = val;            
    }

    @Override
    public void resizeY(int val) {
        float cy = getCenter().getY();
        float rf = (float) val / getHeight();
        for(Layer l : layers)
        {
            float ny = l.getY() - cy;
            ny *= rf;
            l.setY((int) (ny + val / 2f));
        }
        //height = val;
    }

    @Override
    public void addWidthRel(int dif, int i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addHeightRel(int dif, int i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public HashMap<String, Property> getEditableList() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
