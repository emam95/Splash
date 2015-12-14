/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package splash.model;

/**
 *
 * @author Hesham
 */
public class Point {

    private int x;
    private int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * @return the x
     */
    public int getX() {
        return x;
    }

    /**
     * @param x the x to set
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * @return the y
     */
    public int getY() {
        return y;
    }

    /**
     * @param y the y to set
     */
    public void setY(int y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return x + "," + y;
    }

    public PointF toPointF() {
        return new PointF(x, y);
    }

    public Point subtract(Point p) {
        return subtract(p.getX(), p.getY());
    }

    public Point add(int x, int y) {
        return new Point(this.x + x, this.y + y);
    }

    public Point subtract(int x, int y) {
        return new Point(this.x - x, this.y - y);
    }
}
