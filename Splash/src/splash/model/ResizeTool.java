/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package splash.model;

import javafx.scene.paint.Color;

/**
 *
 * @author Hesham
 */
class ResizeTool extends Tool {

    public ResizeTool() {
        id = "Resize";
    }
    
    @Override
    public void select()
    {
        super.select();
        
    }
    @Override
    public void primaryKey(int x, int y, Color col) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseMoved(int x, int y) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void secKey() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
