package splash.model;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public abstract class Polygon extends Object2D {

    int[] xs = new int[0];
    int[] ys = new int[0];
    boolean pointschanged = false;
    private boolean isFilled =  true;

    public boolean getIsFilled() {
        return this.isFilled;
    }

    /**
     *
     * @param isFilled
     */
    public void setIsFilled(boolean isFilled) {
        this.isFilled = isFilled;
    }

    /**
     *
     * @param points
     */
    public void setPoints(Point... points) {
        int i = 0;
        xs = new int[points.length];
        ys = new int[points.length];
        while (i < points.length) {
            xs[i] = points[i].getX();
            ys[i] = points[i++].getY();
        }
        pointschanged = true;
    }

    @Override
    public BufferedImage getBitmap() {
        int w = getWidth(), h = getHeight();
        BufferedImage output = new BufferedImage(w + 1, h + 1, BufferedImage.TYPE_INT_ARGB);
        Graphics2D gpx = output.createGraphics();
        java.awt.Color col = new java.awt.Color(Helper.getARGB(getColor()), true);
        gpx.setColor(col);
        if (getIsFilled()) {
            gpx.fillPolygon(xs, ys, Math.min(xs.length, ys.length));
        } else {
            gpx.drawPolygon(xs, ys, Math.min(xs.length, ys.length));
        }
        return output;
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
        int max = 0;
        for (int i = 0; i < xs.length; i++) {
            max = Math.max(xs[i], max);
        }
        return width = max;
    }

    @Override
    public int getHeight() {
        int max = 0;
        for (int i = 0; i < ys.length; i++) {
            max = Math.max(ys[i], max);
        }
        return height = max;
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
}
