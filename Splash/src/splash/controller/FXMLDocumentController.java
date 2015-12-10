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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ListView;
import javafx.scene.image.PixelWriter;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import splash.model.Helper;
import splash.model.Layer;
import splash.model.ObjectLayer;
import splash.model.RawLayer;
import splash.model.ResourceManager;
import splash.model.ShortcutManager;
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
    private ListView<String> layersList;
    @FXML
    private ListView<String> toolsList;
    @FXML
    private Button newLayBtn;
    @FXML
    private Button delLayBtn;
    @FXML
    private Canvas drawingCanvas;

    private Tool selectedTool;

    private ArrayList<Tool> tools;

    ArrayList<KeyCode> pressedkeys = new ArrayList<>();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        GUIMgr.init(this);
        pw = drawingCanvas.getGraphicsContext2D().getPixelWriter();
        ObservableList<String> items = FXCollections.observableArrayList();
        // Tools are loaded in Splash.java
        tools = ResourceManager.getTools();
        for (Tool tool : tools) {
            items.add(tool.getId());
        }
        toolsList.setItems(items);
        colorPicker.setValue(Color.BLACK);
        toolsList.getSelectionModel().select(0);
        selectedTool = tools.get(0);
        selectedTool.select();

        // events
        drawingCanvas.setOnMousePressed(this::canvasMousePressed);
        drawingCanvas.setOnMouseMoved(this::canvasMouseMoved);
        
        
    }
    
    public void CanvasSize(int width,int height)
    {
        drawingCanvas.setWidth(width);
        drawingCanvas.setHeight(height);
    }

    public Tool toolSelected() {
        return selectedTool;
    }

    public int layerSelected() {
        return Integer.parseInt(layersList.getSelectionModel().getSelectedItem());
    }

    private void canvasMousePressed(MouseEvent ev) {
        if (ev.getButton() == MouseButton.PRIMARY) {
            GUIMgr.getWorkSpace().primaryKey((int) ev.getX(), (int) ev.getY(), GUIMgr.getSelectedTool(), colorPicker.getValue());
        } else if (ev.getButton() == MouseButton.SECONDARY) {
            GUIMgr.getWorkSpace().secKey();
        }
    }

    private void canvasMouseMoved(MouseEvent ev) {
        GUIMgr.getWorkSpace().mouseMoved((int) ev.getX(), (int) ev.getY());
    }

    Color getPixel(int x, int y) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    PixelWriter pw;

    void setPixel(int x, int y, Color col) {
        pw.setArgb(x, y, Helper.getARGB(col));
    }

    @FXML
    private void addLayer(ActionEvent e) {
        GUIMgr.newLayer(new RawLayer());
    }

    @FXML
    private void removeLayer(ActionEvent e) {
        String id = layersList.getSelectionModel().getSelectedItem();
        GUIMgr.removeLayer(Integer.parseInt(id));
    }

    public void refreshLayers() {
        ObservableList<String> items = FXCollections.observableArrayList();
        Layer[] layers = GUIMgr.getWorkSpace().getLayers();
        for (Layer layer : layers) {
            items.add(String.valueOf(layer.getId()));
        }
        layersList.setItems(items);

    }

    @FXML
    private void selectTool(MouseEvent event) {
        String id = toolsList.getSelectionModel().getSelectedItem();
        for (Tool tool : tools) {
            if (tool.getId().equals(id)) {
                selectedTool = tool;
            }
        }
        selectedTool.select();
    }

    @FXML
    private void selectLayer(MouseEvent event) {
        GUIMgr.getWorkSpace().selectLayer(layerSelected());
    }

    @FXML
    private void keyPressed(KeyEvent event) {
        pressedkeys.add(event.getCode());
            ShortcutManager.checkComb(pressedkeys);
    }

    @FXML
    private void keyReleased(KeyEvent event) {
        pressedkeys.remove(event.getCode());
    }
}
