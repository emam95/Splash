package splash.model;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;

public class Polygon extends Object2D {

    int[] xs;
    int[] ys;

    public Polygon(Point... points) {
        setPoints(points);
    }

    /**
     *
     * @param points
     */
    public void setPoints(Point[] points) {
        int i = 0;
        xs = new int[points.length];
        ys = new int[points.length];
        while (i < points.length) {
            xs[i] = points[i].x;
            ys[i++] = points[i].y;
        }
    }

    @Override
    public BufferedImage getBitmap() {
        BufferedImage output = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics gpx = output.getGraphics();
        gpx.fillPolygon(xs, ys, xs.length);
        return output;
    }

    @Override
    public void setWidth(int val) {
        int cx = getCenter().x;
        float rf = val / getWidth();
        width = val;
        for (int i = 0; i < xs.length; i++) {
            int nx = xs[i];
            int dist = nx - cx;
            dist *= rf;
            xs[i] = cx + dist;
        }
    }

    @Override
    public void setHeight(int val) {
        int cy = getCenter().y;
        float rf = val / getHeight();
        height = val;
        for (int i = 0; i < xs.length; i++) {
            int ny = ys[i];
            int dist = ny - cy;
            dist *= rf;
            xs[i] = cy + dist;
        }
    }
}
