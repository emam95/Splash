package splash.model;

import java.awt.image.BufferedImage;

public abstract class Brush {

    public abstract void drawTo(BufferedImage target, int x, int y);
}
