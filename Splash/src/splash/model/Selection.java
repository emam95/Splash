package splash.model;

import java.awt.Rectangle;
import java.util.ArrayList;

public class Selection {

    /**
     *
     * @param targetrect
     * @param sources
     */
    ArrayList<Anchor> anchors = new ArrayList<>();
    Rectangle rect = new Rectangle(0, 0);

    public Selection() {
    }

    public Selection(Rectangle rect) {
        this.rect = rect;
    }

    private void setBounds(Rectangle targetrect) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }    
}
