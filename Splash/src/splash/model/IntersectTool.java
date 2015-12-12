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
public class IntersectTool extends Tool {

    public IntersectTool() {
        this.id = "Intersect";
    }

    @Override
    public void primaryKey(int x, int y, Color col) {
           
    }

    @Override
    public void mouseMoved(int x, int y) {
    }

    @Override
    public void secKey() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
