package splash.model;

import java.awt.Point;

public class Layer {

    private Drawable content;
    private int id;

    /**
     *
     * @param target
     */
    public void draw(WorkSpace target) {
        throw new UnsupportedOperationException();
    }

    public void undo() {
        throw new UnsupportedOperationException();
    }

    public void redo() {
        throw new UnsupportedOperationException();
    }

    public int getId() {
        return this.id;
    }

    /**
     *
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    public Drawable getDrawable() {
        throw new UnsupportedOperationException();
    }

    /**
     *
     * @param scale
     */
    public void resize(float scale) {
        throw new UnsupportedOperationException();
    }

    /**
     *
     * @param diff
     */
    public void transform(Point diff) {
        throw new UnsupportedOperationException();
    }

    /**
     *
     * @param rad
     */
    public void rotate(float rad) {
        throw new UnsupportedOperationException();
    }

    /**
     *
     * @param target
     */
    public void erase(Point target) {
        throw new UnsupportedOperationException();
    }

    /**
     *
     * @param point
     * @return
     */
    public Selection getSelection(Point point) {
        throw new UnsupportedOperationException();
    }
}
