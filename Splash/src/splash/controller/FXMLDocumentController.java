/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package splash.controller;

import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ListView;
import javafx.scene.control.ToolBar;
import javafx.scene.control.cell.ComboBoxListCell;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
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
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ObservableList<String> items =FXCollections.observableArrayList();
        ResourceManager.loadTools();
        ArrayList<Tool> tools = ResourceManager.getTools();
        for(Tool tool : tools)
        {
            items.add(tool.getId());
        }
        toolsList.setItems(items);
    }    

    
}
