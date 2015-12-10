package splash.model;

import javafx.scene.paint.Color;

public class ObjectLayer extends Layer {

    private final Object2D content;
    private int id;

    public ObjectLayer(Object2D drawable) {
        content = drawable;
    }

    public void redraw() {
        bitmap = content.getBitmap();
    }

    @Override
    public void undo() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void redo() {
        throw new UnsupportedOperationException();
    }

    public Drawable getObj() {
        return content;
    }

    @Override
    public void resizeX(int newwidth) {
        content.setWidth(newwidth);
        redraw();
    }

    @Override
    public void resizeY(int newheight) {
        content.setHeight(newheight);
        redraw();
    }

    /**
     *
     * @param diff
     */
    @Override
    public void rotate(float rad) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void erase(Point target) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Selection getSelection(Point point) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    Point startp, cpoint;

    public void primaryKey(int x, int y, Color col) {
        content.primaryKey(new Point(x, y), col);
        redraw();
    }

    public boolean isDrawing() {
        return content.isdrawing();
    }

    public void mouseMoved(int x, int y) {
        if (isDrawing()) {
            content.mouseMoved(new Point(x, y));
            redraw();
            if (content.layerShouldAdjust()) {
                setX(Math.min(x, content.getDrawingStartPoint().getX()));
                setY(Math.min(y, content.getDrawingStartPoint().getY()));
            }
        }
    }

    public void finishDrawing() {
        redraw();
    }

    public java.awt.Rectangle getRect() {
        boolean bmpe = bitmap != null;
        return new java.awt.Rectangle(getX(), getY(), bmpe ? bitmap.getWidth() : 0, bmpe ? bitmap.getHeight() : 0);
    }
}
