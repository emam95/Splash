/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package splash.ui;

import java.nio.file.Paths;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import splash.controller.GUIMgr;
import splash.model.ResourceManager;

public class Splash extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));

        Scene scene = new Scene(root);

        stage.setTitle("Splash");

        stage.setScene(scene);
        //stage.setResizable(false);
        stage.show();        
        //Alert al = new Alert(Alert.AlertType.INFORMATION);
        //al.setContentText(Paths.get("").toAbsolutePath().toString());
        //al.show();
        
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ResourceManager.loadDrawables("\\drawables");
        ResourceManager.loadBrushes("\\Brushes");
        ResourceManager.loadTools();
        GUIMgr.newProject(1440, 900);
        launch(args);
    }

}
