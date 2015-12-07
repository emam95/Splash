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
public class DrawableTool extends Tool {

    private final String drawableName;

    public DrawableTool(String drawableName) {
        this.drawableName = drawableName;
    }

    public String getDrawableName() {
        return drawableName;
    }

    @Override
    public void initFunction(Point startpoint) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
