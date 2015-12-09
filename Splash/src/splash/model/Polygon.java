package splash.model;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Polygon extends Object2D {

    int[] xs;
    int[] ys;
    boolean pointschanged = false;

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
        BufferedImage output = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics gpx = output.getGraphics();
        java.awt.Color col = new java.awt.Color(Helper.getARGB(getColor()), true);
        gpx.setColor(col);
        gpx.fillPolygon(xs, ys, Math.min(xs.length, ys.length));
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
        if (val < 10) {
            return;
        }
        if (val == getWidth()) {
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
        if (!pointschanged) {
            return Math.max(width, 1);
        } else {
            int max = Integer.MIN_VALUE;
            for (int i = 0; i < xs.length; i++) {
                max = Math.max(xs[i], max);
            }
            pointschanged = false;
            return width = max;
        }
    }

    @Override
    public int getHeight() {
        if (!pointschanged) {
            return Math.max(height, 1);
        } else {
            int max = Integer.MIN_VALUE;
            for (int i = 0; i < ys.length; i++) {
                max = Math.max(ys[i], max);
            }
            pointschanged = false;
            return height = max;
        }
    }
    int sox = 1, soy = 1;

    @Override
    public void mouseMoved(Point newpos) {
        if ((newpos.getX() - dstart.getX()) * sox < 0) {
            sox *= -1;
            mirrorXs();
        }
        if ((newpos.getY() - dstart.getY()) * soy < 0) {
            soy *= -1;
            mirrorYs();
        }
        super.mouseMoved(newpos);
    }

    private void mirrorXs() {
        for (int i = 0; i < xs.length; i++) {
            xs[i] = getWidth() - xs[i];
        }
    }

    private void mirrorYs() {
        for (int i = 0; i < ys.length; i++) {
            ys[i] = getHeight() - ys[i];
        }
    }
}
