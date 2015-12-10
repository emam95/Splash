package splash.controller;

import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import splash.model.*;

public class GUIMgr {
    
    private static FXMLDocumentController cont = null;
    
    public static void init(FXMLDocumentController controller) {
        cont = controller;
        
        ShortcutManager.subscribe(new KeyCode[]{KeyCode.CONTROL, KeyCode.Z}, new OnShortcutHandler(){
            @Override
            public void shortcutUsed() {
                CommandCenter.Undo();
            }            
        });
        
        ShortcutManager.subscribe(new KeyCode[]{KeyCode.CONTROL, KeyCode.Y}, new OnShortcutHandler(){
            @Override
            public void shortcutUsed() {
                CommandCenter.Redo();
            }            
        });
    }
    
    public static void clearDrawingArea() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public static WorkSpace getWorkSpace() {
        return workspace;
    }
    
    private static WorkSpace workspace = null;

    /**
     *
     * @param drawable
     */
    public static Tool getSelectedTool() {
        return cont.toolSelected();
    }
    
    public static Color getPixel(int x, int y) {
        return cont.getPixel(x, y);
    }
    
    public static void setPixel(int x, int y, Color col) {
        cont.setPixel(x, y, col);
    }
    
    public static void newProject(int width, int height) {
        workspace = new WorkSpace(width, height);
    }
    
    public static boolean isDrawing() {
        return workspace.isDrawing();
    }
    
    public static void clearPixel(int x, int y) {
        setPixel(x, y, null);
    }
    
    public static void newLayer(Layer layer) {
        CommandCenter.ExecuteCommand(new Command() {
            @Override
            public void execute() {
                workspace.addLayer(layer);
                cont.refreshLayers();
            }
            
            @Override
            public void unexecute() {
                removeLayer(layer.getId());
            }
        });
    }
    
    public static void removeLayer(int id) {
        workspace.removeLayer(id);
        cont.refreshLayers();
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
    
    public void startDrawing(int x, int y, Tool tool, Color col) {
        workspace.primaryKey(x, y, tool, col);
    }
    
    public void mouseMoved(int x, int y) {
        workspace.mouseMoved(x, y);
    }
    
    public void finishDrawing() {
        workspace.secKey();
    }
}
