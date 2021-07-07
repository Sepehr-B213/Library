package controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class MainController {

    private double x,y;

    static Stage stage = null;

    public Parent load(String address) throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource(address));
            loader.load();
            return loader.getRoot();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void show(Parent parent) {
        if (stage == null) {
            stage = new Stage();
            stage.setScene(new Scene(parent));
            stage.initStyle(StageStyle.UNDECORATED);

            parent.setOnMousePressed(event -> {
                x = event.getSceneX();
                y = event.getSceneY();
            });
            parent.setOnMouseDragged(event -> {
                stage.setX(event.getScreenX() - x);
                stage.setY(event.getScreenY() - y);
            });
            stage.show();
        }
    }

    public void close(Node node) {
        ((Stage)node.getScene().getWindow()).close();
    }

    public void minimize(Node node) { ((Stage)node.getScene().getWindow()).setIconified(true); }


    public void clearField(Node... nodes) {
        for(Node node : nodes)
            ((TextField)node).clear();
    }
}