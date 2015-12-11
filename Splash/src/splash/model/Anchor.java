package splash.model;

public abstract class Anchor {

    boolean isactive = false;
    int x = 0, y = 0;
    int id = 0;

    public Anchor(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Anchor(int id) {
        this.id = id;
    }

    public void primaryDown(int x, int y) {
        isactive = true;
    }

    public void secDown(int x, int y) {
        isactive = false;
    }
    int lastxdif = 0, lastydif = 0;

    public void move(int x, int y) {
        if (isactive) {
            lastxdif = x - this.x;
            lastydif = y - this.y;
            this.x = x;
            this.y = y;
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    void setX(int x) {
        this.x = x;
    }

    void setY(int y) {
        this.y = y;
    }

    public int getId() {
        return id;
    }

    void deactivate() {
        isactive = false;
    }

    void activate() {
        isactive = true;
    }
}
