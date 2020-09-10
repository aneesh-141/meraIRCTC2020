package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.TextFlow;

import java.io.IOException;

public class Login {

    @FXML AnchorPane anclog;
    @FXML PasswordField password;
    @FXML TextField email;
    @FXML Label lab;

    DBHelper dbHelper = new DBHelper();

    public void register(MouseEvent mouseEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Register.fxml"));
        anclog.getChildren().setAll(root);
    }

    public void loginuser(MouseEvent mouseEvent) throws IOException {

        boolean response = dbHelper.login(email.getText(), password.getText());

        if (response){

            FXMLLoader loader = new FXMLLoader(getClass().getResource("profile.fxml"));
            Parent root = loader.load();
            anclog.getChildren().setAll(root);

            Profile profile = loader.getController();
            String name = dbHelper.searchname(email.getText());
            String uid = dbHelper.searchuid(email.getText());

            profile.display(name, uid, email.getText());


        }else {
                lab.setText("LOGIN UNSUCCESSFUL");
        }
    }
}


