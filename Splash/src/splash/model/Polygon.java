package splash.model;

import java.awt.Point;

public class Polygon extends Object2D {

    private Point[] points;

    public Point[] getPoints() {
        return this.points;
    }

    /**
     *
     * @param points
     */
    public void setPoints(Point[] points) {
        this.points = points;
    }

}
