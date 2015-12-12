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

    static ResizeRectangle instance = null;

    static ResizeRectangle getInstance() {
        return instance;
    }

    Layer target;
    Rectangle trec;

    @Override
    public void secKey() {
        if (selected == null) {
            return;
        }
        selected.secDown();
        selected = null;
        Layer ct = target;
        Rectangle ts = target.getRect();
        Rectangle or = trec;
        CommandCenter.StoreCommand(new Command() {
            @Override
            public void execute() {
                ct.setX((int) ts.getX());
                ct.setY((int) ts.getY());
                ct.resizeX((int) ts.getWidth());
                ct.resizeY((int) ts.getHeight());
                GUIMgr.getWorkSpace().selectLayer(ct.getId());
                GUIMgr.getWorkSpace().redrawRegion(ts, or);
            }

            @Override
            public void unexecute() {
                ct.setX((int) or.getX());
                ct.setY((int) or.getY());
                ct.resizeX((int) or.getWidth());
                ct.resizeY((int) or.getHeight());
                GUIMgr.getWorkSpace().selectLayer(ct.getId());
                GUIMgr.getWorkSpace().redrawRegion(ts, or);
            }
        });
    }

    @Override
    void mouseMoved(int x, int y) {
        if (selected != null) {
            Rectangle orect = getRect();
            selected.move(x - getX(), y - getY());
            if (target.wasMirroredX()) {
                int i = selected.getId();
                int j;
                if (i % 2 == 1) {
                    j = (i + 4) % 8;
                } else {
                    switch (i) {
                        case 0:
                            j = 2;
                            break;
                        case 2:
                            j = 0;
                            break;
                        case 6:
                            j = 4;
                            break;
                        case 4:
                            j = 6;
                            break;
                        default:
                            j = i;
                    }
                }
                anchors[i].deactivate();
                (selected = anchors[j]).activate();
            }
            if (target.wasMirroredY()) {
                int i = selected.getId();
                int j;
                if (i % 2 == 1) {
                    j = (i + 4) % 8;
                } else {
                    switch (i) {
                        case 0:
                            j = 6;
                            break;
                        case 2:
                            j = 4;
                            break;
                        case 6:
                            j = 0;
                            break;
                        case 4:
                            j = 2;
                            break;
                        default:
                            j = i;
                    }
                }
                anchors[i].deactivate();
                (selected = anchors[j]).activate();
            }
            syncRect();
            redrawBitmap();
            Rectangle nrect = getRect();
            GUIMgr.getWorkSpace().redrawRegion(orect, nrect);            
            GUIMgr.getWorkSpace().supressNextRedraw();
        }
    }
    Anchor nw, n, ne, e, se, s, sw, w;

    final void syncRect() {
        super.setRect(target.getRect(), false);
        for (Anchor anc : anchors) {
            int id = anc.getId();
            anc.setX(id == 0 || id == 7 || id == 6 ? 0 : id == 1 || id == 8 || id == 5 ? (int) (target.getWidth() / 2f) : target.getWidth());
            anc.setY(id == 0 || id == 1 || id == 2 ? 0 : id == 3 || id == 8 || id == 7 ? (int) (target.getHeight() / 2f) : target.getHeight());
        }
    }

    public ResizeRectangle(Layer target) {
        this.target = target;
        trec = target.getRect();
        anchordim = 8;
        nw = new Anchor(0) {
            @Override
            public void move(int x, int y) {
                super.move(x, y);
                if (lastxdif != 0) {
                    target.addWidthRel(lastxdif * -1, 0);
                    lastxdif = 0;
                }
                if (lastydif != 0) {
                    target.addHeightRel(lastydif * -1, 0);
                    lastydif = 0;
                }
            }

        };
        n = new Anchor(1) {
            @Override
            public void move(int x, int y) {
                super.move(x, y);
                if (lastxdif != 0) {
                    this.x -= lastxdif;
                    lastxdif = 0;
                }
                if (lastydif != 0) {
                    target.addHeightRel(lastydif * -1, 1);
                    lastydif = 0;
                }
            }

        };
        ne = new Anchor(2) {
            @Override
            public void move(int x, int y) {
                super.move(x, y);
                if (lastxdif != 0) {
                    target.addWidthRel(lastxdif, 2);
                    lastxdif = 0;
                }
                if (lastydif != 0) {
                    target.addHeightRel(lastydif * -1, 2);
                    lastydif = 0;
                }
            }

        };
        e = new Anchor(3) {
            @Override
            public void move(int x, int y) {
                super.move(x, y);
                if (lastxdif != 0) {
                    target.addWidthRel(lastxdif, 3);
                    lastxdif = 0;
                }
                if (lastydif != 0) {
                    this.y -= lastydif;
                    lastydif = 0;
                }
            }

        };
        se = new Anchor(4) {
            @Override
            public void move(int x, int y) {
                super.move(x, y);
                if (lastxdif != 0) {
                    target.addWidthRel(lastxdif, 4);
                    lastxdif = 0;
                }
                if (lastydif != 0) {
                    target.addHeightRel(lastydif, 4);
                    lastydif = 0;
                }
            }

        };
        s = new Anchor(5) {
            @Override
            public void move(int x, int y) {
                super.move(x, y);
                if (lastxdif != 0) {
                    this.x -= lastxdif;
                    lastxdif = 0;
                }
                if (lastydif != 0) {
                    target.addHeightRel(lastydif, 5);
                    lastydif = 0;
                }
            }

        };
        sw = new Anchor(6) {
            @Override
            public void move(int x, int y) {
                super.move(x, y);
                if (lastxdif != 0) {
                    target.addWidthRel(lastxdif * -1, 6);
                    lastxdif = 0;
                }
                if (lastydif != 0) {
                    target.addHeightRel(lastydif, 6);
                    lastydif = 0;
                }
            }

        };
        w = new Anchor(7) {
            @Override
            public void move(int x, int y) {
                super.move(x, y);
                if (lastxdif != 0) {
                    target.addWidthRel(lastxdif * -1, 7);
                    lastxdif = 0;
                }
                if (lastydif != 0) {
                    this.y -= lastydif;
                    lastydif = 0;
                }
            }

        };
        setAnchors(new Anchor[]{nw, n, ne, e, se, s, sw, w});
        syncRect();
        redrawBitmap();
        instance = this;
    }
}
