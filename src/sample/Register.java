package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.awt.*;
import java.io.IOException;

public class Register {

    @FXML AnchorPane ancpane;
    @FXML TextField name;
    @FXML TextField email;
    @FXML TextField password;
    @FXML Label status;

    DBHelper dbHelper = new DBHelper();

    public void login(MouseEvent mouseEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        ancpane.getChildren().setAll(root);
    }

    public void register(MouseEvent mouseEvent) {
        boolean response = dbHelper.register(name.getText(), email.getText(), password.getText());
        if (response){
            status.setText("REGISTRATION SUCCESSFUL! PLEASE LOGIN!");
        }else {
            status.setText("REGISTRATION UNSUCCESSFUL");
        }
    }
}
