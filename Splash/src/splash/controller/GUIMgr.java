package splash.controller;

import splash.model.*;

public class GUIMgr {

    private WorkSpace workspace;

    /**
     *
     * @param drawable
     */
    public static Tool getSelectedTool() {
        return splash.model.ResourceManager.getTools().get(0);
    }

    public static void notifyRegionUpdate(int x, int y, int w, int h) {

    }

    public void startDrawing(Drawable drawable) {
        throw new UnsupportedOperationException();
    }

    public void newProject() {
        throw new UnsupportedOperationException();
    }

    public void newLayer() {
        throw new UnsupportedOperationException();
    }

    /**
     *
     * @param id
     */
    public void selecLayer(int id) {
        throw new UnsupportedOperationException();
    }

    public void initSave() {
        throw new UnsupportedOperationException();
    }

    public void initLoad() {
        throw new UnsupportedOperationException();
    }

}
