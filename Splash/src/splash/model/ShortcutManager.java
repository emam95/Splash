/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package splash.model;

import java.util.ArrayList;
import java.util.HashMap;
import javafx.scene.input.KeyCode;

/**
 *
 * @author Hesham
 */
public class ShortcutManager {

    static HashMap<KeyCode[], OnShortcutHandler> shortcuts = new HashMap<>();

    /**
     *
     * @param comb
     * @param handler
     */
    public static void subscribe(KeyCode[] comb, OnShortcutHandler handler) {
        shortcuts.put(comb, handler);
    }

    public static void checkComb(ArrayList<KeyCode> currentcomb) {
        for (KeyCode[] comb : shortcuts.keySet()) {
            if (comb.length != currentcomb.size()) {
                continue;
            }
            boolean mismatch = false;
            for (int i = 0; i < comb.length; i++) {
                if (currentcomb.get(i) != comb[i]) {
                    mismatch = true;
                    break;
                }
            }
            if (mismatch) {
                continue;
            }
            shortcuts.get(comb).shortcutUsed();
            break;
        }
    }
}
