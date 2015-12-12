/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package splash.model;

import java.util.HashMap;

/**
 *
 * @author Hesham
 */
public class BrushFactory {
    public static HashMap<String, Class<? extends Brush>> Brushes = new HashMap<>();

    /**
     *
     * @param type
     * @return
     */
    public static Brush createBrush(String type) {
        if (Brushes.containsKey(type)) {
            try {
                Class<? extends Brush> cl = Brushes.get(type);
                return cl.newInstance();
            } catch (InstantiationException | IllegalAccessException ex) {
                System.out.println("BrushFactory failed to construct object.");
                ex.printStackTrace();
            }
        }
        return null;
    }
}
