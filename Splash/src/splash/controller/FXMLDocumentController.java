/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package splash.controller;

import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ListView;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

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
    private ListView<?> toolsList;
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
        
    }    

    
}
