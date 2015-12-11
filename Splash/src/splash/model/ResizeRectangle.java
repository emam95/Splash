/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package splash.model;

import java.awt.Rectangle;
import splash.controller.GUIMgr;

/**
 *
 * @author Hesham
 */
public class ResizeRectangle extends Selection {

    Layer target;

    @Override
    void mouseMoved(int x, int y) {
        if (selected != null) {
            //Rectangle orect = getRect();
            selected.move(x - getX(), y - getY());
            //Rectangle nrect = getRect();            
            redrawBitmap();
            //GUIMgr.getWorkSpace().redrawRegion(orect, nrect, true);
        }
    }
    Anchor nw, n, ne, e, se, s, sw, w;

    public ResizeRectangle(Layer target) {
        this.target = target;
        setRect(target.getRect(), false);
        int wd = target.getWidth(), h = target.getHeight();
        nw = new Anchor(0, 0) {
            @Override
            public void move(int x, int y) {
                super.move(x, y);
                if (lastxdif != 0) {
                    target.addWidthRel(lastxdif * -1, 0);
                    w.setX(getX());
                    sw.setX(getX());
                    lastxdif = 0;
                }
                if (lastydif != 0) {
                    target.addHeightRel(lastydif * -1, 0);
                    n.setY(getY());
                    ne.setY(getY());
                    lastydif = 0;
                }
            }

        };
        n = new Anchor((int) (wd / 2f), 0) {
            @Override
            public void move(int x, int y) {
                super.move(x, y);
                if (lastxdif != 0) {
                    this.x -= lastxdif;
                    lastxdif = 0;
                }
                if (lastydif != 0) {
                    target.addHeightRel(lastydif * -1, 1);
                    nw.setY(getY());
                    ne.setY(getY());
                    lastydif = 0;
                }
            }

        };
        ne = new Anchor(wd, 0) {
            @Override
            public void move(int x, int y) {
                super.move(x, y);
                if (lastxdif != 0) {
                    target.addWidthRel(lastxdif, 2);
                    e.setX(getX());
                    se.setX(getX());
                    lastxdif = 0;
                }
                if (lastydif != 0) {
                    target.addHeightRel(lastydif * -1, 2);
                    nw.setY(getY());
                    n.setY(getY());
                    lastydif = 0;
                }
            }

        };
        e = new Anchor(wd, (int) (h / 2f)) {
            @Override
            public void move(int x, int y) {
                super.move(x, y);
                if (lastxdif != 0) {
                    target.addWidthRel(lastxdif, 3);
                    ne.setX(getX());
                    se.setX(getX());
                    lastxdif = 0;
                }
                if (lastydif != 0) {
                    this.y -= lastydif;
                    lastydif = 0;
                }
            }

        };
        se = new Anchor(wd, h) {
            @Override
            public void move(int x, int y) {
                super.move(x, y);
                if (lastxdif != 0) {
                    target.addWidthRel(lastxdif, 4);
                    ne.setX(getX());
                    e.setX(getX());
                    lastxdif = 0;
                }
                if (lastydif != 0) {
                    target.addHeightRel(lastydif, 4);
                    s.setY(getY());
                    sw.setY(getY());
                    lastydif = 0;
                }
            }

        };
        s = new Anchor((int) (wd / 2f), h) {
            @Override
            public void move(int x, int y) {
                super.move(x, y);
                if (lastxdif != 0) {
                    this.x -= lastxdif;
                    lastxdif = 0;
                }
                if (lastydif != 0) {
                    target.addHeightRel(lastydif, 5);
                    se.setY(getY());
                    sw.setY(getY());
                    lastydif = 0;
                }
            }

        };
        sw = new Anchor(0, h) {
            @Override
            public void move(int x, int y) {
                super.move(x, y);
                if (lastxdif != 0) {
                    target.addWidthRel(lastxdif * -1, 6);
                    w.setX(getX());
                    nw.setX(getX());
                    lastxdif = 0;
                }
                if (lastydif != 0) {
                    target.addHeightRel(lastydif, 6);
                    s.setY(getY());
                    se.setY(getY());
                    lastydif = 0;
                }
            }

        };
        w = new Anchor(0, (int) (h / 2f)) {
            @Override
            public void move(int x, int y) {
                super.move(x, y);
                if (lastxdif != 0) {
                    target.addWidthRel(lastxdif * -1, 7);
                    nw.setX(getX());
                    sw.setX(getX());
                    lastxdif = 0;
                }
                if (lastydif != 0) {
                    this.y -= lastydif;
                    lastydif = 0;
                }
            }

        };
        setAnchors(new Anchor[]{nw, n, ne, e, se, s, sw, w});
        redrawBitmap();
    }
}
