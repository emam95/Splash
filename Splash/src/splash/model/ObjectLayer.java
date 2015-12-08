package splash.model;

import javafx.scene.paint.Color;

public class ObjectLayer extends Layer {

    private final Object2D content;
    private int id;

    public ObjectLayer(int x, int y, Object2D drawable) {
        super(x, y);
        System.out.println(x + "," + y + "lc");
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
        // TODO handle move
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

    public void startDrawing(int x, int y, Color col) {
        System.out.println(x + "," + y + "layer|");
        content.startDrawing(new Point(x, y), col);
        redraw();
    }

    public void mouseMoved(int x, int y) {
        content.mouseMoved(new Point(x, y));
        redraw();
        //this.x = Math.min(content.getDrawingStartPoint().getX(), content.getCurrentDrawingPos().getX());
        //this.y = Math.min(content.getDrawingStartPoint().getY(), content.getCurrentDrawingPos().getY());
        System.out.println(content.getDrawingStartPoint().getX() + "," + content.getDrawingStartPoint().getY());
        System.out.println("lp" + x + "," + y);
    }

    public void finishDrawing() {
        redraw();
    }

    public java.awt.Rectangle getRect() {
        return new java.awt.Rectangle(getX(), getY(), content.getWidth(), content.getHeight());
    }
}
