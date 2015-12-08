package splash.model;

import java.awt.Point;

public class ObjectLayer extends Layer {

    int x, y;
    private final Object2D content;
    private int id;

    public ObjectLayer(int x, int y, Object2D drawable) {
        this.x = x;
        this.y = y;
        content = drawable;
        this.id = idseed++;
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
    public void transform(Point diff) {
        x += diff.x;
        y += diff.y;
    }

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

    public void startDrawing(int x, int y) {
        content.startDrawing(new Point(x, y));
        redraw();
    }

    public void mouseOffset(int x, int y) {
        content.mouseOffset(new Point(x, y));
        redraw();
        this.x = Math.min(content.getDrawingStartPoint().x, content.getCurrentDrawingPos().x);
        this.y = Math.min(content.getDrawingStartPoint().y, content.getCurrentDrawingPos().y);
    }

    public void finishDrawing() {
        redraw();
    }

    public java.awt.Rectangle getRect() {
        return new java.awt.Rectangle(x, y, content.getWidth(), content.getHeight());
    }
}
