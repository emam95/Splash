package splash.model;

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import javafx.scene.paint.Color;

public class Selection {

    /**
     *
     * @param targetrect
     * @param sources
     */
    BufferedImage bitmap = null;
    Anchor[] anchors = new Anchor[0];
    int x, y, width, height;
    int anchordim = 0;

    public Selection() {
    }

    public BufferedImage redrawBitmap() {
        BufferedImage output = new BufferedImage(width + anchordim, height + anchordim, BufferedImage.TYPE_INT_ARGB);
        Graphics2D gpx = output.createGraphics();
        gpx.setColor(java.awt.Color.ORANGE);
        gpx.setStroke(new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{4, 2, 2, 2}, 0));
        int hanc = anchordim / 2;
        gpx.drawPolygon(new int[]{hanc, width + hanc - 1, width + hanc - 1, hanc}, new int[]{hanc, hanc, height + hanc - 1, height + hanc - 1}, 4);
        for (Anchor a : anchors) {
            gpx.fill3DRect(a.getX(), a.getY(), anchordim, anchordim, true);
        }
        return bitmap = output;
    }

    public BufferedImage getBitmap() {
        return bitmap;
    }

    public Selection(Rectangle rect) {
        setRect(rect, true);
    }

    void clear() {
        bitmap = null;
    }

    void setRect(Rectangle rect, boolean redraw) {
        x = (int) rect.getX();
        y = (int) rect.getY();
        width = (int) rect.getWidth();
        height = (int) rect.getHeight();
        if (redraw) {
            redrawBitmap();
        }
    }

    public Color getPixel(int sx, int sy) {
        if (bitmap == null) {
            return null;
        }
        int rx = sx - getX(), ry = sy - getY();
        java.awt.Color awcol = new java.awt.Color(bitmap.getRGB(rx, ry), true);
        int r, g, b, a;
        r = awcol.getRed();
        g = awcol.getGreen();
        b = awcol.getBlue();
        a = awcol.getAlpha();
        return new Color(r / 255.0, g / 255.0, b / 255.0, a / 255.0);
    }

    public int getX() {
        return x - anchordim / 2;
    }

    public int getY() {
        return y - anchordim / 2;
    }

    public int getW() {
        if (bitmap == null) {
            return 0;
        }
        return bitmap.getWidth();
    }

    public int getH() {
        if (bitmap == null) {
            return 0;
        }
        return bitmap.getHeight();
    }

    Rectangle getRect() {
        return new Rectangle(getX(), getY(), getW(), getH());
    }

    void setAnchors(Anchor[] anchors) {
        this.anchors = anchors;
    }
    Anchor selected = null;

    void secKey() {
        if (selected == null) {
            return;
        }
        selected.secDown(x, y);
        selected = null;
    }

    void mouseMoved(int x, int y) {
        if (selected != null) {
            selected.move(x - getX(), y - getY());
        }
    }

    void primaryKey(int x, int y) {
        int rx = x - getX(), ry = y - getY();
        for (Anchor anc : anchors) {
            if (rx >= anc.getX() && rx <= anc.getX() + anchordim && ry >= anc.getY() && ry <= anc.getY() + anchordim) {
                selected = anc;
                selected.primaryDown(x, y);
                break;
            }
        }
    }

}
