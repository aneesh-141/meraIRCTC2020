package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.PasswordField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import java.awt.*;
import java.io.IOException;

public class Controller {
    @FXML Pane rootpane;

    public void register(MouseEvent mouseEvent) throws IOException {
        //Launch Register.fxml
        Parent root = FXMLLoader.load(getClass().getResource("Register.fxml"));
        rootpane.getChildren().setAll(root);
    }

    public void login(MouseEvent mouseEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        rootpane.getChildren().setAll(root);
    }
}
