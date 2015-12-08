package splash.model;

import java.awt.Color;
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
        //pointschanged = true;
    }

    @Override
    public BufferedImage getBitmap() {
        int w = getWidth(), h = getHeight();
        BufferedImage output = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics gpx = output.getGraphics();
        java.awt.Color col = new java.awt.Color(Helper.getARGB(getColor()), true);
        gpx.setColor(col);
        gpx.fillRect(0, 0, width, height);
        return output;
    }

    @Override
    public void setWidth(int val) {
        if (val == 0) {
            return;
        }
        int cx = getCenter().getX();
        float rf = val / getWidth();
        width = val;
        //System.out.println("Width=" + val);
        /*for (int i = 0; i < xs.length; i++) {
            int nx = xs[i];
            int dist = nx - cx;
            dist *= rf;
            xs[i] = cx + dist;
        }*/
    }

    @Override
    public void setHeight(int val) {
        if (val == 0) {
            return;
        }
        int cy = getCenter().getY();
        float rf = val / getHeight();
        height = val;
        //System.out.println("Height=" + val);
        /*for (int i = 0; i < xs.length; i++) {
            int ny = ys[i];
            int dist = ny - cy;
            dist *= rf;
            xs[i] = cy + dist;
        }*/
    }

    @Override
    public int getWidth() {
        if (!pointschanged) {
            return Math.max(width, 1);
        } else {
            int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
            for (int i = 0; i < xs.length; i++) {
                min = Math.min(xs[i], min);
                max = Math.max(xs[i], max);
            }
            pointschanged = false;
            return width = max - min;
        }
    }

    @Override
    public int getHeight() {
        if (!pointschanged) {
            return Math.max(height, 1);
        } else {
            int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
            for (int i = 0; i < ys.length; i++) {
                min = Math.min(ys[i], min);
                max = Math.max(ys[i], max);
            }
            pointschanged = false;
            return height = max - min;
        }
    }
}
