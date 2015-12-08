/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package splash.model;

import java.awt.Point;

/**
 *
 * @author Hesham
 */
public class PointerTool extends Tool {
    
    public PointerTool()
    {
        id = "Pointer";
    }

    @Override
    public void initFunction(Point startpoint) {
        // TODO: Handle move
    }

    @Override
    public void startDrawing(int x, int y) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseOffset(int x, int y) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void finishDrawing() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
