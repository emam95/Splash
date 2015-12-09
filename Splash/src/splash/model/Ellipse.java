package splash.model;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import javafx.scene.paint.Color;

public class Ellipse extends Object2D {

    private PointF center;
    private int attribute;

    public PointF getCenter() {
        return this.center;
    }

    /**
     *
     * @param center
     */
    public void setCenter(PointF center) {
        this.center = center;
    }

    @Override
    public BufferedImage getBitmap() {
        BufferedImage output = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D gpx = output.createGraphics();
        java.awt.Color col = new java.awt.Color(Helper.getARGB(getColor()), true);
        gpx.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        gpx.setColor(col);
        gpx.fillOval(0, 0, width, height);
        return output;
    }

    @Override
    public void startDrawing(Point start, Color col) {
        setCenter(start.toPointF());
        super.startDrawing(start, col);
    }

}
