/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package splash.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import splash.model.Helper;
import splash.model.ResourceManager;
import splash.model.Tool;

/**
 * FXML Controller class
 *
 * @author MEmam
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private ColorPicker colorPicker;
    @FXML
    private ListView<?> layersList;
    @FXML
    private ListView<String> toolsList;
    @FXML
    private Button newLayBtn;
    @FXML
    private Button delLayBtn;
    @FXML
    private Canvas drawingCanvas;

    private ArrayList<Tool> tools;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        GUIMgr.init(this);
        ObservableList<String> items = FXCollections.observableArrayList();
        // Tools are loaded in Splash.java
        tools = ResourceManager.getTools();
        for (Tool tool : tools) {
            items.add(tool.getId());
        }
        toolsList.setItems(items);

        // events
        drawingCanvas.setOnMousePressed(this::canvasMousePressed);
        drawingCanvas.setOnMouseMoved(this::canvasMouseMoved);
    }

    public Tool toolSelected() {
        String id = toolsList.getSelectionModel().getSelectedItem();
        for (Tool tool : tools) {
            if (tool.getId().equals(id)) {
                return tool;
            }
        }
        return null;
    }

    private void canvasMousePressed(MouseEvent ev) {
        if (!GUIMgr.isDrawing()) {
            GUIMgr.getWorkSpace().startDrawing((int) ev.getX(), (int) ev.getY(), GUIMgr.getSelectedTool(), colorPicker.getValue());
        } else {
            GUIMgr.getWorkSpace().finishDrawing();
        }
    }

    private void canvasMouseMoved(MouseEvent ev) {
        GUIMgr.getWorkSpace().mouseMoved((int) ev.getX(), (int) ev.getY());
    }

    Color getPixel(int x, int y) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    void setPixel(int x, int y, Color col) {
        drawingCanvas.getGraphicsContext2D().getPixelWriter().setArgb(x, y, Helper.getARGB(col));
    }    
}
