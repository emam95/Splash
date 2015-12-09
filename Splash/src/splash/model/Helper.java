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
public class Helper {

    public static int getARGB(Color col) {
        if (col == null) {
            return 0;
        }
        int a = (int) (col.getOpacity() * 255);
        int r = (int) (col.getRed() * 255);
        int g = (int) (col.getGreen() * 255);
        int b = (int) (col.getBlue() * 255);
        return b | (g << 8) | (r << 16) | (a << 24);
    }
}
