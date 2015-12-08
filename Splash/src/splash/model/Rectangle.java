package splash.model;

public class Rectangle extends Polygon {

    public Rectangle() {
        super.setPoints(new Point(0, 0), new Point(1, 0), new Point(0, 1), new Point(1, 1));
    }
}
