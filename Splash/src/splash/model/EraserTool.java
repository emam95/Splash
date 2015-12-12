/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package splash.model;

import com.sun.prism.paint.Color;

/**
 *
 * @author Hesham
 */
public class EraserTool extends BrushTool {

    public EraserTool() {
        brush = new RoundBrush(RoundBrush.defaultstrokesize, null);
        this.id = "Eraser";
    }
}
