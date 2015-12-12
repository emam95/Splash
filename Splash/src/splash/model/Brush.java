package splash.model;

import java.awt.image.BufferedImage;
import javafx.scene.paint.Color;

public abstract class Brush {
    Color color;
    
    public abstract void drawTo(BufferedImage target, int x, int y);

    public abstract int getWidth();

    public abstract int getHeight();
}
