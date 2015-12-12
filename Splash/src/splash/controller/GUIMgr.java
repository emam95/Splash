package splash.controller;

import java.util.Optional;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.util.Pair;
import splash.model.*;

public class GUIMgr {

    private static FXMLDocumentController cont = null;

    public static void init(FXMLDocumentController controller) {
        cont = controller;
        CreateNewProject();

        ShortcutManager.subscribe(new KeyCode[]{KeyCode.CONTROL, KeyCode.Z}, new OnShortcutHandler() {
            @Override
            public void shortcutUsed() {
                CommandCenter.Undo();
            }
        });

        ShortcutManager.subscribe(new KeyCode[]{KeyCode.CONTROL, KeyCode.Y}, new OnShortcutHandler() {
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

    public static void CreateNewProject() {
        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setTitle("New Project");
        dialog.setHeaderText("Create New Project");

        ButtonType okButton = new ButtonType("Done", ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(okButton, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField nameField = new TextField();
        nameField.setPromptText("New Project");
        TextField dimensions = new TextField();
        dimensions.setPromptText("1920 x 1080");

        grid.add(new Label("Project Name:"), 0, 0);
        grid.add(nameField, 1, 0);
        grid.add(new Label("Dimensions:"), 0, 1);
        grid.add(dimensions, 1, 1);

        dialog.getDialogPane().setContent(grid);

        Platform.runLater(() -> nameField.requestFocus());

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == okButton) {
                if (nameField.getText().equals("") || dimensions.getText().equals("")) {
                    return new Pair<>("New Project", "1920 x 1080");
                }
                return new Pair<>(nameField.getText(), dimensions.getText());
            }
            return new Pair<>("New Project", "1920 x 1080");
        });

        Optional<Pair<String, String>> result = dialog.showAndWait();

        result.ifPresent(projectinfo -> {
            int width = Integer.parseInt(projectinfo.getValue().split("x", 2)[0].trim());
            int length = Integer.parseInt(projectinfo.getValue().split("x", 2)[1].trim());
            newProject(projectinfo.getKey(), width, length);
        });
    }

    public static void newProject(String name, int width, int height) {
        cont.CanvasSize(width, height);
        workspace = new WorkSpace(width, height);
    }

    public static boolean isDrawing() {
        return workspace.isDrawing();
    }

    public static void clearPixel(int x, int y) {
        setPixel(x, y, null);
    }

    public static void newLayer(Layer layer) {
        int id = layer.getId();
        CommandCenter.ExecuteCommand(new Command() {
            @Override
            public void execute() {
                workspace.addLayer(layer);
                cont.refreshLayers();
            }

            @Override
            public void unexecute() {
                removeLayer(id);
            }
        });
    }

    public static void removeLayer(int id) {
        workspace.removeLayer(id);
        cont.refreshLayers();
    }

    public static boolean isKeyPressed(KeyCode key) {
        return cont.iskeyPressed(key);
    }

    public static void triedToEditObject() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
