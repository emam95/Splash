package splash.model;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import javafx.scene.paint.Color;

public abstract class Polygon extends Object2D {

    float[] xs = new float[0];
    float[] ys = new float[0];
    boolean pointschanged = false;

    /**
     *
     * @param points
     */
    public void setPoints(Point... points) {
        int i = 0;
        xs = new float[points.length];
        ys = new float[points.length];
        while (i < points.length) {
            xs[i] = points[i].getX();
            ys[i] = points[i++].getY();
        }
        pointschanged = true;
    }

    @Override
    public void setWidth(int val) {
        if (val < 3) {
            return;
        }
        if (val == getWidth()) {
            return;
        }
        float cx = getCenter().getX();
        float rf = (float) val / getWidth();
        width = val;
        for (int i = 0; i < xs.length; i++) {
            float nx = xs[i] - cx;
            nx *= rf;
            xs[i] = (int) (nx + val / 2f);
        }
    }

    @Override
    public void setHeight(int val) {
        if (val < 3) {
            return;
        }
        if (val == getHeight()) {
            return;
        }
        float cy = getCenter().getY();
        float rf = (float) val / getHeight();
        height = val;
        for (int i = 0; i < ys.length; i++) {
            float ny = ys[i] - cy;
            ny *= rf;
            ys[i] = (int) (ny + val / 2f);
        }
    }

    @Override
    public int getWidth() {
        float max = 0;
        for (int i = 0; i < xs.length; i++) {
            max = Math.max(xs[i], max);
        }
        return width = (int) max;
    }

    @Override
    public int getHeight() {
        float max = 0;
        for (int i = 0; i < ys.length; i++) {
            max = Math.max(ys[i], max);
        }
        return height = (int) max;
    }
    int sox = 1, soy = 1;

    @Override
    public void mouseMoved(Point newpos) {
        Point p = newpos.subtract(dstart);
        if (p.getX() * sox < 0) {
            sox *= -1;
            mirrorXs();
        }
        if (p.getY() * soy < 0) {
            soy *= -1;
            mirrorYs();
        }
        int nx = 0, ny = 0;
        if (p.getX() < 0) {
            if (parent != null) {
                parent.adjustX(p.getX());
            }
        }
        if (p.getY() < 0) {
            if (parent != null) {
                parent.adjustY(p.getY());
            }
        }
        super.mouseMoved(newpos);
    }

    private void mirrorXs() {
        getWidth();
        for (int i = 0; i < xs.length; i++) {
            xs[i] = width - xs[i];
        }
    }

    private void mirrorYs() {
        getHeight();
        for (int i = 0; i < ys.length; i++) {
            ys[i] = height - ys[i];
        }
    }

    @Override
    public void drawToBitmap(BufferedImage target, int x, int y) {
        int w = getWidth(), h = getHeight();
        Graphics2D gpx = target.createGraphics();
        int fxcol = getFillColor();
        int cancol = getCanvasColor();
        int[] ixs = new int[xs.length];
        int[] iys = new int[ys.length];
        for (int i = 0; i < ixs.length; i++) {
            ixs[i] = (int) xs[i];
            iys[i] = (int) ys[i];
        }
        if (getIsFilled()) {
            if (nullifyfill) {
                gpx.setColor(null);
            } else {
                gpx.setColor(new java.awt.Color(fxcol, true));
            }
            gpx.fillPolygon(ixs, iys, Math.min(xs.length, ys.length));
            gpx.setColor(new java.awt.Color(cancol, true));
            gpx.drawPolygon(ixs, iys, Math.min(xs.length, ys.length));
        } else {
            gpx.drawPolygon(ixs, iys, Math.min(xs.length, ys.length));
        }
    }
}
